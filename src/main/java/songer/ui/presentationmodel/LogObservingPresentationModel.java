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
package songer.ui.presentationmodel;

import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import songer.logback.EventAppender;



public class LogObservingPresentationModel  {
    private static final Logger logger = LoggerFactory.getLogger(LogObservingPresentationModel.class);
    private ValueHolder textModel = new ValueHolder("");
    private ValueHolder caretPositionModel = new ValueHolder(0);

    // CAREFUL: This needs to be a field
    private EventAppender.LogEventListener listener = new EventAppender.LogEventListener() {
        @Override
        public void onLogEvent(String logMessage) {
            textModel.setValue( textModel.getString() + logMessage );
            caretPositionModel.setValue( textModel.getString().length() );
        }
    };

    public LogObservingPresentationModel() {
        EventAppender<?> eventAdapter = EventAppender.getByName("UI-APPENDER");
        if (eventAdapter != null) {
            eventAdapter.addListener(listener);
        }


        logger.info("Handler initialized !");
    }




    public ValueModel getTextModel() {
        return textModel;
    }
    
    public ValueModel getCaretPositionModel() {
       return caretPositionModel;
    }
}