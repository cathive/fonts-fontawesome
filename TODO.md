We still need an automated mechanism to convert the contents of icons.yml to our Java source code.

The easiest way to use YAML from within Java seems to be the "SnakeYAML" dependency:

```xml
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.12</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
```

The little helper whose output I use to create the file "FontAwesomeIcons.java" looks like that:

```java
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

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.Iterator;
import java.util.List;

/**
 * @author Benjamin P. Jung
 */
public class _FontAwesomeGenerator {

    public static void main(String[] args) throws Exception {
        final Yaml yaml = new Yaml(new Constructor(IconDescriptorList.class));
        final IconDescriptorList icons = yaml.loadAs(_FontAwesomeGenerator.class.getResourceAsStream("icons.yml"), IconDescriptorList.class);
        for (final IconDescriptor icon: icons) {
            System.out.print("/**\n");
            System.out.print(" * " + icon.name + "\n");
            if (icon.aliases != null && !icon.aliases.isEmpty()) {
                System.out.print(" * <p>aliases:</p>\n");
                System.out.print(" * <ul>\n");
                for (String alias: icon.aliases) {
                    System.out.print(" *   <li>" + alias + "</li>\n");
                }
                System.out.print(" * </ul>\n");
            }
            if (icon.categories != null && !icon.categories.isEmpty()) {
                System.out.print(" * <p>Categories:</p>\n");
                System.out.print(" * <ul>\n");
                for (String category: icon.categories) {
                    System.out.print(" *   <li>" + category + "</li>\n");
                }
                System.out.print(" * </ul>\n");
            }
            if (icon.created != null && !icon.created.isEmpty()) {
                System.out.print(" * @since " + icon.created + "\n");
            }
            System.out.print(" */\n");
            System.out.print("public static final String " + icon.id.toUpperCase().replaceAll("-", "_") + " = \"&#" + icon.unicode + ";\";\n");
            System.out.print("\n");

        }
    }


    private static class IconDescriptorList implements Iterable<IconDescriptor> {
        public List<IconDescriptor> icons;
        @Override
        public Iterator<IconDescriptor> iterator() {
            return this.icons.iterator();
        }
    }

    private static class IconDescriptor {
        public String name;
        public String id;
        public String unicode;
        public String created;
        public List<String> aliases;
        public List<String> categories;
    }
}
```