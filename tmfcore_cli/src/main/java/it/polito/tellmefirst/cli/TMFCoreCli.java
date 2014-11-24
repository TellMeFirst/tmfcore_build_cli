/*-
 * TellMeFirst - A Knowledge Discovery Application
 *
 * Copyright (C) 2014 Simone Basso <bassosimone@gmail.com>.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.polito.tellmefirst.cli;

import it.polito.tellmefirst.classify.Classifier;
import it.polito.tellmefirst.lucene.IndexesUtil;
import it.polito.tellmefirst.util.TMFVariables;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

public class TMFCoreCli {
	public static void main(String[] args)
			throws InterruptedException, IOException,
			org.apache.lucene.queryParser.ParseException,
			org.apache.commons.cli.ParseException {

		Options options = new Options();
		options.addOption("f", true, "Input file");
		options.addOption("l", true, "Classifier language (e.g., it)");
		options.addOption("n", true, "Number of topics");

		CommandLineParser parser = new PosixParser();
		CommandLine cmdline = parser.parse(options, args);

		if (!cmdline.hasOption("f") || !cmdline.hasOption("l") ||
				!cmdline.hasOption("n")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("tellmefirst", options);
			return;
		}

		String filePath = cmdline.getOptionValue("f");
		String content = new String(Files.readAllBytes(
				Paths.get(filePath)), "utf-8");

		String lang = cmdline.getOptionValue("l");

		String numTopics = cmdline.getOptionValue("n");
		Integer topics = Integer.parseInt(numTopics);

		TMFVariables tmfVariables = new TMFVariables("conf/server.properties");
		IndexesUtil.init();

		Classifier classifier = new Classifier(lang);
		List<String[]> list = classifier.classify(content, topics, lang);

		System.out.println("=== BEGIN CLASSIFY OUTPUT ===");
		for (String[] v : list) {
			System.out.println("--- BEGIN CLASSIFY OUTPUT ENTRY ---");
			for (String s : v) {
				System.out.println(s);
			}
			System.out.println("--- END CLASSIFY OUTPUT ENTRY ---");
		}
		System.out.println("=== END CLASSIFY OUTPUT ===");
	}
}
