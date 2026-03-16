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

package consulo.svg.path.language.impl.syntax;

import consulo.language.ast.IElementType;
import consulo.language.ast.TokenType;
import consulo.language.lexer.LexerBase;
import consulo.svg.path.language.psi.SvgPathTypes;

%%

%public
%class _SvgPathLexer
%extends LexerBase
%function advanceImpl
%type IElementType
%unicode
%eof{  return;
%eof}

WHITE_SPACE=[ \t\r\n]+
NUMBER=[+-]?([0-9]+(\.[0-9]*)?|\.[0-9]+)([eE][+-]?[0-9]+)?

%%

{WHITE_SPACE}               { return TokenType.WHITE_SPACE; }

[Mm]                        { return SvgPathTypes.MOVE_TO; }
[Ll]                        { return SvgPathTypes.LINE_TO; }
[Hh]                        { return SvgPathTypes.HORIZONTAL_LINE_TO; }
[Vv]                        { return SvgPathTypes.VERTICAL_LINE_TO; }
[Cc]                        { return SvgPathTypes.CURVE_TO; }
[Ss]                        { return SvgPathTypes.SMOOTH_CURVE_TO; }
[Qq]                        { return SvgPathTypes.QUADRATIC_TO; }
[Tt]                        { return SvgPathTypes.SMOOTH_QUADRATIC_TO; }
[Aa]                        { return SvgPathTypes.ARC_TO; }
[Zz]                        { return SvgPathTypes.CLOSE_PATH; }

{NUMBER}                    { return SvgPathTypes.NUMBER; }

","                         { return SvgPathTypes.COMMA; }

[^]                         { return TokenType.BAD_CHARACTER; }
