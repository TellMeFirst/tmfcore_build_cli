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
import java.io.IOException;
import java.util.List;
import org.apache.lucene.queryParser.ParseException;

public class TMFCoreCli {
	public static void main(String[] args)
			throws InterruptedException, IOException, ParseException {
		Classifier classifier = new Classifier("it");
		List<String[]> list = classifier.classify("Hello, world", 7, "it");
		System.out.println(list + "\n");
	}
}
