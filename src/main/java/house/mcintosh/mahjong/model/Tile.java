package house.mcintosh.mahjong.model;

/**
 * A tile in a hand.
 */
public final class Tile
{

	//
	// The ordering of these enumerations affects the sort order of Sets in a Hand.
	//
	
	public enum Suit
	{
		CHARACTERS, BAMBOO, CIRCLES
	}

	public enum Number
	{
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;

		Number next()
		{
			return Number.values()[this.ordinal() + 1];
		}
	}

	public enum Dragon
	{
		RED, GREEN, WHITE
	}

	public enum Type
	{
		DRAGON, WIND, SUIT
	}

	private final Suit m_suit;
	private final Number m_number;
	private final Wind m_wind;
	private final Dragon m_dragon;
	private final Type m_type;

	private Tile(Suit suit, Number number, Wind wind, Dragon dragon, Type type)
	{
		this.m_suit = suit;
		this.m_number = number;
		this.m_wind = wind;
		this.m_dragon = dragon;
		this.m_type = type;
	}

	public Tile(Suit suit, Number number)
	{
		this(suit, number, null, null, Type.SUIT);
	}

	public Tile(Wind wind)
	{
		this(null, null, wind, null, Type.WIND);
	}

	public Tile(Dragon dragon)
	{
		this(null, null, null, dragon, Type.DRAGON);
	}

	public Suit getSuit()
	{
		return m_suit;
	}

	public Number getNumber()
	{
		return m_number;
	}

	public Wind getWind()
	{
		return m_wind;
	}

	public Dragon getDragon()
	{
		return m_dragon;
	}

	public Type getType()
	{
		return m_type;
	}

	public boolean isMajor()
	{
		switch (this.m_type)
		{
		case SUIT:
			switch (this.m_number)
			{
			case ONE:
			case NINE:
				return true;
			default:
				return false;
			}

		default:
			return true;
		}
	}

	/**
	 * Creates a new tile in same suit as the current tile but with the next number
	 * in sequence.
	 * 
	 * Only valid to be called for SUIT types with numbers up to EIGHT.
	 */
	public Tile createNextNumber()
	{
		return new Tile(this.m_suit, this.m_number.next());
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append('[');
			
		switch(m_type)
		{
		case DRAGON:
			sb.append(m_dragon).append(" DRAGON");
			break;
			
		case WIND:
			sb.append(m_wind).append(" WIND");
			break;
			
		case SUIT:
			sb.append(m_number).append(' ').append(m_suit);
			break;
		}
		
		sb.append(']');
		
		return sb.toString();
	}
}
