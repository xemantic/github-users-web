/*
 * github-users-web - lists GitHub users. Minimal app demonstrating
 * cross-platform app development (Web, Android, iOS) where core
 * logic is shared and transpiled from Java to JavaScript and
 * Objective-C. This project delivers Web version.
 *
 * Copyright (C) 2017  Kazimierz Pogoda
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xemantic.githubusers.web.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A tool which transforms the output of GWT compiler into valid Closure Compiler input.
 *
 * @author morisil
 */
public class PrepareGwtJs {

  private final static Pattern PAYLOAD_ATTRIBUTE_PATTERN = Pattern.compile("_payload\\.([a-zA-Z0-9_]+)");

  private static final Set<String> FUNCTIONS_TO_REMOVE = new HashSet<>(Arrays.asList(
      "__gwt_isKnownPropertyValue",
      "__gwt_getMetaProperty"
  ));

  private FunctionProcessingStage lookFor = FunctionProcessingStage.UNKNOWN;

  public static void main(String ... args) throws IOException {
    Path inFile = Paths.get(args[0]);
    Path outFile = Paths.get(args[1]);
    System.out.println("PrepareGwtJs: " + inFile + " -> " + outFile);
    (new PrepareGwtJs()).process(inFile, outFile);
  }

  private void process(Path inFile, Path outFile) throws IOException {
    try (BufferedReader in = Files.newBufferedReader(inFile)) {
      try (BufferedWriter out = Files.newBufferedWriter(outFile)) {
        process(in, out);
      }
    }
  }

  private void process(BufferedReader in, BufferedWriter out) throws IOException {
    writeHeader(out);
    String line;
    while ((line = in.readLine()) != null) {
      if (shouldSkip(line)) { continue; }
      line = fixStatements(line);
      out.write(line);
      out.write('\n');
    }
  }

  private void writeHeader(BufferedWriter out) throws IOException {
    out.write("/**\n");
    out.write(" * @fileoverview output of GWT compiler processed with PrepareGwtJs tool.\n");
    out.write(" * @suppress {checkTypes, checkVars, suspiciousCode, uselessCode, duplicate, es5Strict}\n");
    out.write(" */\n");
    out.write("goog.module('app');\n");
    out.write("goog.require('com.xemantic.githubusers.web.app');\n");
    out.write("goog.require('com.xemantic.ankh.incrementaldom');\n\n");
  }

  private boolean shouldSkip(String line) {
    boolean skip = true;
    if (startsFunction(line, FUNCTIONS_TO_REMOVE)) {
      lookFor = FunctionProcessingStage.RETURN;
    } else if (lookFor == FunctionProcessingStage.LAST_SEMICOLON) {
      if (line.trim().equals(";")) {
        lookFor = FunctionProcessingStage.UNKNOWN;
      }
    } else if (lookFor == FunctionProcessingStage.RETURN) {
      if (line.contains("return")) {
        lookFor = FunctionProcessingStage.LAST_SEMICOLON;
      }
    } else {
      skip = false;
    }
    return skip;
  }

  private boolean startsFunction(String line, Set<String> functions) {
    return functions.stream().anyMatch(line::contains);
  }

  private String fixStatements(String line) {
    line = line.replace("$wnd.com.xemantic", "com.xemantic");
    line = line.replace("$wnd.mdc", "mdc");
    line = line.replace("$wnd.Object", "Object");

    Matcher matcher = PAYLOAD_ATTRIBUTE_PATTERN.matcher(line);
    if (matcher.find()) {
      line = matcher.replaceAll("_payload['$1']");
    }
    return line;
  }

  private enum FunctionProcessingStage {
    UNKNOWN,
    RETURN,
    LAST_SEMICOLON
  }

}
