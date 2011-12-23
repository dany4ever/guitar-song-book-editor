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

import songer.util.FileList;
import songer.gui.MainFrame;

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

			FileList fileList = new FileList(args[0], new FileList.TxtFileFilter(), new FileList.FileNameComparator());
			MainFrame mainFrame = new MainFrame(args[0]+"/songer.css", fileList);
			mainFrame.setVisible(true);
		} catch (Exception ex) {
			System.err.println("Exception occured during initialization :" + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
