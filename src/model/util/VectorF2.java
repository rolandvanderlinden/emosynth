package model.util;

/**
 * //Oren Becker, http://ragestorm.net
 * 
 */
public class VectorF2 implements Cloneable
{
	public float x, y;

	public VectorF2()
	{
		this(0, 0);
	}

	public VectorF2(float newX, float newY)
	{
		x = newX;
		y = newY;
	}

	/**
	 * This clones this vec2.
	 */
	@Override
	public VectorF2 clone()
	{
		return new VectorF2(x, y);
	}

	/**
	 * This method tells us if the other object is this object.
	 */
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof VectorF2)
		{
			VectorF2 v = (VectorF2) other;
			return (this.x == v.x && this.y == v.y);
		}
		else
			return false;
	}

	/**
	 * This returns a string representation of this vector2.
	 */
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
	
	
	// ********************************
	// STATIC FUNCTIONS
	// ********************************
	
	/**
	 * This returns the substracted vector.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static VectorF2 Subtract(VectorF2 a, VectorF2 b)
	{
		VectorF2 result = new VectorF2(0, 0);

		result.x = a.x - b.x;
		result.y = a.y - b.y;

		return result;
	}

	/**
	 * This returns the substracted vector.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static VectorF2 Add(VectorF2 a, VectorF2 b)
	{
		VectorF2 result = new VectorF2(0, 0);

		result.x = a.x + b.x;
		result.y = a.y + b.y;

		return result;
	}

	/**
	 * This returns the result of the multiplication between a number and a vector2.
	 * @param f
	 * @param a
	 * @return
	 */
	public static VectorF2 Multiply(float f, VectorF2 a)
	{
		VectorF2 result = new VectorF2();
		
		result.x = f * a.x;
		result.y = f * a.y;
		
		return result;
	}
	
	/**
	 * This method rotates the given VectorF2 according to the angle.
	 * 
	 * @param v
	 * @param ang
	 */
	public static VectorF2 RotateClockwise(VectorF2 v, float ang)
	{
		float t;
		float cosa = (float)Math.cos(ang);
		float sina = (float)Math.sin(ang);

		t = v.x;
		v.x = t * cosa + v.y * sina;
		v.y = -t * sina + v.y * cosa;

		return v;
	}

	/**
	 * This method calculates the eucledian distance between vector a and b.
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static float EucledianDistance(VectorF2 A, VectorF2 B)
	{
		if (A == null || B == null) return -1;

		return (float)Math.sqrt(Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.y, 2));
	}

	/**
	 * This returns the length of this vector.
	 * 
	 * @param a
	 * @return
	 */
	public static float GetLength(VectorF2 a)
	{
		return (float)Math.sqrt(Math.pow(a.x, 2) + Math.pow(a.y, 2));
	}
	
	/**
	 * This returns the dot-product of the two vector2s.
	 * @param A
	 * @param B
	 * @return
	 */
	public static float DotProduct(VectorF2 A, VectorF2 B)
	{
		return (A.x * B.x) + (A.y * B.y);
	}
	
	/**
	 * This returns a new vector that is the exact middle of the two vectors.
	 * @param A
	 * @param B
	 * @return
	 */
	public static VectorF2 middle(VectorF2 A, VectorF2 B)
	{
		return VectorF2.interpolate(A, B, 1, 1);
	}
	
	/**
	 * This returns a new vector that is the interpolation between two vectors and some weights.
	 * @param A
	 * @param B
	 * @param weightA
	 * @param weightB
	 * @return
	 */
	public static VectorF2 interpolate(VectorF2 A, VectorF2 B, float weightA, float weightB)
	{
		VectorF2 result = new VectorF2();
		result.x = ((weightA * A.x) + (weightB * B.x)) / (weightA + weightB);
		result.y = ((weightA * A.y) + (weightB * B.y)) / (weightA + weightB);
		return result;
	}	
}