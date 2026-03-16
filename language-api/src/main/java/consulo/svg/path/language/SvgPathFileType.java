/*
 * Copyright 2013-2026 consulo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package consulo.svg.path.language;

import consulo.svg.path.language.icon.SvgPathIconGroup;
import consulo.svg.path.language.localize.SvgPathLocalize;
import consulo.language.file.LanguageFileType;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;
import jakarta.annotation.Nonnull;

public class SvgPathFileType extends LanguageFileType {
    public static final SvgPathFileType INSTANCE = new SvgPathFileType();

    protected SvgPathFileType() {
        super(SvgPathLanguage.INSTANCE);
    }

    @Override
    public @Nonnull String getId() {
        return "SvgPath";
    }

    @Override
    public @Nonnull LocalizeValue getDescription() {
        return SvgPathLocalize.filetypeSvgpathDescription();
    }

    @Override
    public @Nonnull String getDefaultExtension() {
        return "svgpath";
    }

    @Override
    public Image getIcon() {
        return SvgPathIconGroup.svgpath();
    }
}
