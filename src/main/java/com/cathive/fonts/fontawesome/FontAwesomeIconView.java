/*
 * Copyright (c) 2013 The Cat Hive Developers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.cathive.fonts.fontawesome;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

import java.util.concurrent.Callable;

/**
 * @author Benjamin P. Jung
 */
public class FontAwesomeIconView extends Label {

    private final ObjectProperty<FontAwesomeIcon> icon = new SimpleObjectProperty<>();

    public FontAwesomeIconView() {
        super();
        this.getStyleClass().add("font-awesome-icon-view");
        this.getStylesheets().add("com/cathive/fonts/fontawesome/FontAwesomeIconView.css");
        this.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                final FontAwesomeIcon icon = FontAwesomeIconView.this.getIcon();
                return String.valueOf(icon == null ? null : icon.getUnicode());
            }
        }, this.iconProperty()));
    }


    public FontAwesomeIconView(final FontAwesomeIcon icon) {
        this();
        setIcon(icon);
    }

    public void setIcon(final FontAwesomeIcon icon) {
        this.icon.set(icon);
    }

    public FontAwesomeIcon getIcon() {
        return this.icon.get();
    }

    public ObjectProperty<FontAwesomeIcon> iconProperty() {
        return this.icon;
    }

}
