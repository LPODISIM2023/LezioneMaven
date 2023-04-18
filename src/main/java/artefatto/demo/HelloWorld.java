package artefatto.demo;

import static artefatto.demo.business.SupportoNumeriUtil.goldbach;
import static artefatto.demo.business.SupportoNumeriUtil.isPrime;

import java.time.Duration;
import java.time.Instant;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class HelloWorld {

	/**
	 * Questa è il metodo main
	 * 
	 * @param args parametri presi dalla command line. Sono necessari 2 parametri:
	 *             il primo è il numero intero da verificare se è primo, il secondo
	 *             è il numero intero sul quale verificare la congettura di Goldbach
	 */
	public static void main(String[] args) {
		Options ops = new Options();

		OptionGroup optionGroup = new OptionGroup();
		// Option o = new Option("prima", "calcola se il valore è primo");

		Option primeMode = Option.builder("primo").argName("prima").desc("Calcola se il valore è primo").build();

		Option goldbackMode = Option.builder("goldback").argName("goldbac")
				.desc("Calcola se la congettura di goldback è valida per il valore").build();

		optionGroup.addOption(goldbackMode);
		optionGroup.addOption(primeMode);
		optionGroup.setRequired(true);
		Option value = Option.builder().option("value").required().type(Integer.class).argName("value").desc("Valore")
				.hasArg().build();
		Option help = Option.builder().option("help").argName("help").desc("stampa messaggio di aiuto").build();
		ops.addOption(help);
		ops.addOptionGroup(optionGroup);
		ops.addOption(value);
		CommandLineParser clp = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		try {
			// parse the command line arguments
			CommandLine line = clp.parse(ops, args);
			Integer numeroDaVerificare = Integer.parseInt(line.getOptionValue(value));
			if (line.hasOption(help))
				formatter.printHelp("Ecco la stampa di aiuto", ops);

			if (line.hasOption(primeMode)) {
				System.out.printf("il numero %d %s è primo\n", numeroDaVerificare,
						isPrime(numeroDaVerificare) ? "" : "non ");
				isPrime(numeroDaVerificare);
			}

			if (line.hasOption(goldbackMode)) {
				Instant inizio = Instant.now();
				System.out.printf("Il numero %d %s verifica la concettura di Goldbach\n", numeroDaVerificare,
						goldbach(numeroDaVerificare) ? "" : "non");
				Duration timeElapsed = Duration.between(inizio, Instant.now());
				System.out.printf("Tempo trascorso: %d millisecondi\n", timeElapsed.toMillis());
			}

		} catch (ParseException exp) {
			formatter.printHelp("Esempio di chiamata", ops);
			System.out.println("Parsing failed.  Reason: " + exp.getMessage());

		}

	}

}