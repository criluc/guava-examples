package it.cnr.iit.examples.guava;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Multimap;
import com.google.common.io.Resources;

public class Fluents {
	
	static class Provincia {
		String sigla;
		String nome;
		String regione;
		
		Provincia(String nome, String regione, String sigla) {
			this.nome = nome;
			this.sigla = sigla;
			this.regione = regione;
		}
		
		@Override
		public String toString() {
			return Objects.toStringHelper(this).add("nome", nome).toString();
		}
	}
	
	
	public enum ProvinciaFromString implements Function<String, Provincia> {
		INSTANCE;
		
		private final Splitter CSVSPLITTER = Splitter.on(",");
		
		@Override
		public Provincia apply(String line) {
			Iterator<String> iter = CSVSPLITTER.split(line).iterator();
			return new Provincia(iter.next(), iter.next(), iter.next());
		}
	}
	
	public enum RegioneFromProvincia implements Function<Provincia, String> {
		INSTANCE;
		
		@Override
		public String apply(Provincia p) {
			return p.regione;
		}
	}
	
	public List<Provincia> loadProvince() throws IOException {
		List<String> lines = Resources.readLines(Resources
				.getResource(Fluents.class, "provincia-regione-sigla.csv"),  
				Charsets.UTF_8);
		
		return FluentIterable.from(lines)
				.transform(ProvinciaFromString.INSTANCE).toList();
	}
	
	
	
	@Test
	public void groupBy() throws IOException {
		Multimap<String, Provincia> perRegione = FluentIterable
				.from(loadProvince())
				.index(RegioneFromProvincia.INSTANCE);

		System.out.println(perRegione);
		
		Collection<Provincia> toscana = perRegione.get("Toscana");
		assertEquals(toscana.size(), 10);
		
		assertEquals(FluentIterable.from(toscana).anyMatch(new Predicate<Provincia>() {

			@Override
			public boolean apply(Provincia input) {
				return "PI".equalsIgnoreCase(input.sigla);
			}
		}), true);
	}
}
