package it.cnr.iit.examples.guava;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

public class OurRange {

	@Test
	public void myrange() {
		Range<Integer> almenoDieci = Range.atLeast(10);
		
		assertTrue(almenoDieci.contains(11));
		assertFalse(almenoDieci.contains(9));
	}

	@Test
	public void rangefilter() {
		Range<Float> tra2e3 = Range.closed(2f, 3f);
		
		List<Float> list = ImmutableList.of(1.0f, 2.1f, 2.2f, 3.1f);
		
		assertEquals(FluentIterable.from(list).filter(tra2e3).size(), 2);
	}
	
	@Test
	public void rangeset() {

		RangeSet<Integer> myranges = TreeRangeSet.create();
		myranges.add(Range.closedOpen(1,  10));
		myranges.add(Range.closedOpen(10, 20));
		myranges.add(Range.closedOpen(20, 50));
		System.out.println(myranges);
		
		assertTrue(myranges.encloses(Range.closed(1, 5)));
	}
}
