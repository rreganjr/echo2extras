/* 
 * This file is part of the Echo2 Extras Project.
 * Copyright (C) 2005-2006 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo2.extras.app.menu;

import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

import nextapp.echo2.app.event.ChangeEvent;
import nextapp.echo2.app.event.ChangeListener;
import nextapp.echo2.app.event.EventListenerList;

/**
 * Default <code>MenuSelectionModel</code> implementation.
 */
public class DefaultMenuSelectionModel 
implements MenuSelectionModel {

    private EventListenerList listenerList = new EventListenerList();
    private Set selection = new HashSet();

    /**
     * @see nextapp.echo2.extras.app.menu.MenuSelectionModel#addChangeListener(nextapp.echo2.app.event.ChangeListener)
     */
    public void addChangeListener(ChangeListener l) {
        listenerList.addListener(ChangeListener.class, l);
    }

    /**
     * @see nextapp.echo2.extras.app.menu.MenuSelectionModel#isSelected(java.lang.Object)
     */
    public boolean isSelected(Object id) {
        return selection.contains(id);
    }
    
    /**
     * Notifies <code>ChangeListener</code>s of a selection state change.
     */
    protected void fireSelectionChanged() {
        ChangeEvent e = new ChangeEvent(this);
        EventListener[] listeners = listenerList.getListeners(ChangeListener.class);
        for (int i = 0; i < listeners.length; ++i) {
            ((ChangeListener) listeners[i]).stateChanged(e);
        }
    }

    /**
     * @see nextapp.echo2.extras.app.menu.MenuSelectionModel#removeChangeListener(nextapp.echo2.app.event.ChangeListener)
     */
    public void removeChangeListener(ChangeListener l) {
        listenerList.removeListener(ChangeListener.class, l);
    }

    /**
     * @see nextapp.echo2.extras.app.menu.MenuSelectionModel#setSelected(java.lang.Object, boolean)
     */
    public void setSelected(Object id, boolean selected) {
        if (selected) {
            selection.add(id);
        } else {
            selection.remove(id);
        }
        fireSelectionChanged();
    }
}