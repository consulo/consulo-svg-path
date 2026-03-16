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

package consulo.svg.path.language.impl.parsing;

import consulo.svg.path.language.SvgPathFileType;
import consulo.language.file.LanguageFileType;
import consulo.test.junit.impl.language.SimpleParsingTest;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;

public class SvgPathParsingTest extends SimpleParsingTest<Object> {
    public SvgPathParsingTest() {
        super("parsing", "svgpath");
    }

    @Test
    public void testSimple(Context context) throws Exception {
        doTest(context, null);
    }

    @Test
    public void testMoveTo(Context context) throws Exception {
        doTest(context, null);
    }

    @Test
    public void testCurves(Context context) throws Exception {
        doTest(context, null);
    }

    @Test
    public void testArcs(Context context) throws Exception {
        doTest(context, null);
    }

    @Test
    public void testComplex(Context context) throws Exception {
        doTest(context, null);
    }

    @Test
    public void testNoSpaces(Context context) throws Exception {
        doTest(context, null);
    }

    @Nonnull
    @Override
    protected LanguageFileType getFileType(@Nonnull Context context, @Nullable Object o) {
        return SvgPathFileType.INSTANCE;
    }
}
