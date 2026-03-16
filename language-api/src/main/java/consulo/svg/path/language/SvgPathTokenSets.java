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

import consulo.svg.path.language.psi.SvgPathTypes;
import consulo.language.ast.TokenSet;

public final class SvgPathTokenSets {
    public static final TokenSet COMMENTS = TokenSet.EMPTY;

    public static final TokenSet STRING_LITERALS = TokenSet.EMPTY;

    public static final TokenSet COMMANDS = TokenSet.create(
            SvgPathTypes.MOVE_TO, SvgPathTypes.LINE_TO, SvgPathTypes.HORIZONTAL_LINE_TO,
            SvgPathTypes.VERTICAL_LINE_TO, SvgPathTypes.CURVE_TO, SvgPathTypes.SMOOTH_CURVE_TO,
            SvgPathTypes.QUADRATIC_TO, SvgPathTypes.SMOOTH_QUADRATIC_TO, SvgPathTypes.ARC_TO,
            SvgPathTypes.CLOSE_PATH
    );

    private SvgPathTokenSets() {
    }
}
