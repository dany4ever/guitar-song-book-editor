/*
 *  Copyright (c) 2008 - Tomas Janecek.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package songer;

import songer.exporter.HtmlExporter;
import songer.exporter.LaTexExporter;
import songer.exporter.PdfExporter;
import songer.ui.UIDialog;
import songer.ui.presentationmodel.MainFormPresentationModel;
import songer.ui.view.MainFormView;
import songer.ui.view.TextDialog;
import songer.util.FileList;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/** 
 * Wrapper class for main function.
 * @author Tomas Janecek
 */
public class Main {

	/**
	 * Program main function.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.err.println("Usage: java -jar ./songer.jar <docs-directory> ");
				System.exit(1);
			}

            // Build objects (could use DI container but the project is too small for that)

            // Dialog provider
            UIDialog<String> newNextFileDialog = new UIDialog<String>() {
                @Override
                public String runDialog(Frame ownerFrame) {
                    TextDialog textDialog = new TextDialog(
                            ownerFrame,
                            Pattern.compile("([A-Za-z]+-)?[A-Z][A-Za-z ]*[A-Za-z]"),
                            "Enter song name",
                            "Enter new song name:");
                    return textDialog.runDialog();
                }
            };

            // Main Presentation Model
            MainFormPresentationModel mainPM = new MainFormPresentationModel(
                    new FileList(args[0], new FileList.TxtFileFilter(), new FileList.FileNameComparator()),
                    newNextFileDialog,
                    new HtmlExporter(),
                    new LaTexExporter(),
                    new PdfExporter()  );

            // Main View
            JFrame mainFrame = new MainFormView( mainPM );
			mainFrame.setVisible(true);
		} catch (Exception ex) {
			System.err.println("Exception occured during initialization :" + ex.getMessage());
			ex.printStackTrace();
		}
	}
}