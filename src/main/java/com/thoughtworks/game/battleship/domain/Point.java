package com.thoughtworks.game.battleship.domain;

public class Point {

	private int rowNumber;
	private int columnNumber;

	public Point(int rowNumber, int columnNumber) {
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + rowNumber;
		result = prime * result + columnNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (rowNumber != other.rowNumber)
			return false;
		if (columnNumber != other.columnNumber)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [rowNumber=" + rowNumber + ", columnNumber=" + columnNumber + "]";
	}
}