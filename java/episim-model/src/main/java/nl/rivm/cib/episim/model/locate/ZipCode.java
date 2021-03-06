package nl.rivm.cib.episim.model.locate;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.coala.json.Wrapper;

/**
 * {@link ZipCode} is a simple {@link Wrapper} of {@link String} values
 * 
 * @version $Id$
 * @author Rick van Krevelen
 */
public class ZipCode extends Place.ID //Wrapper.SimpleOrdinal<String> 
{

	private static final Pattern POSTCODE_PATTERN = Pattern
			.compile( "(\\d+)\\s*([a-zA-Z]{2})" );

	/**
	 * @param value the {@link String} value to wrap
	 * @return the {@link ZipCode} decorator
	 */
	public static ZipCode valueOf( final String value )
	{
		return Util.of( Objects.requireNonNull( value ), new ZipCode() );
	}

	protected Matcher match()
	{
		final Matcher result = POSTCODE_PATTERN.matcher( unwrap() );
		return result.find() ? result : null;
	}

	public String toPostCode3()
	{
		return match().group( 1 ).substring( 0, 3 );
	}

	public String toPostCode4()
	{
		return match().group( 1 );
	}

	public String[] toPostCode6()
	{
		final Matcher m = match();
		return new String[] { m.group( 1 ), m.group( 2 ).toUpperCase() };
	}
}