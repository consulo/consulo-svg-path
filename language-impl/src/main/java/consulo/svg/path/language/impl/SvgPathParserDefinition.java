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

package consulo.svg.path.language.impl;

import consulo.annotation.component.ExtensionImpl;
import consulo.svg.path.language.SvgPathFileElementTypes;
import consulo.svg.path.language.SvgPathLanguage;
import consulo.svg.path.language.SvgPathTokenSets;
import consulo.svg.path.language.impl.psi.SvgPathFileImpl;
import consulo.svg.path.language.impl.psi.SvgPathTypesFactory;
import consulo.svg.path.language.impl.syntax.SvgPathSyntaxParser;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@ExtensionImpl
public class SvgPathParserDefinition implements ParserDefinition {
    @Nonnull
    @Override
    public Language getLanguage() {
        return SvgPathLanguage.INSTANCE;
    }

    @Nonnull
    @Override
    public Lexer createLexer(LanguageVersion languageVersion) {
        return new SvgPathLexer();
    }

    @Nonnull
    @Override
    public PsiParser createParser(LanguageVersion languageVersion) {
        return new SvgPathSyntaxParser();
    }

    @Nonnull
    @Override
    public IFileElementType getFileNodeType() {
        return SvgPathFileElementTypes.SVG_PATH_FILE;
    }

    @Nonnull
    @Override
    public TokenSet getCommentTokens(LanguageVersion languageVersion) {
        return SvgPathTokenSets.COMMENTS;
    }

    @Nonnull
    @Override
    public TokenSet getStringLiteralElements(LanguageVersion languageVersion) {
        return SvgPathTokenSets.STRING_LITERALS;
    }

    @Nonnull
    @Override
    public PsiElement createElement(@Nonnull ASTNode node) {
        return SvgPathTypesFactory.createElement(node);
    }

    @Nonnull
    @Override
    public PsiFile createFile(@Nonnull FileViewProvider viewProvider) {
        return new SvgPathFileImpl(viewProvider);
    }

    @Nonnull
    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(@Nullable ASTNode left, @Nullable ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
