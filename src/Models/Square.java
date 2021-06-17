package Models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Square extends Shape {

	public Square(int x1, int y1, Color color, float storke) {
		super(x1, y1, color, storke);
		this.setType("Square");
	}

	public Square(int x1, int y1, Color color, float storke, boolean drawFilled) {
		super(x1, y1, color, storke, drawFilled);
		this.setType("Square");
	}

	@Override
	public void draw(Graphics g) {
		this.setCornerPt(new Point(Math.min(this.getX1(), this.getX2()), Math.min(this.getY1(), this.getY2())));
		int side = Math.max(Math.abs(this.getY2() - this.getY1()), Math.abs(this.getX2() - this.getX1()));

		this.setHelpingPtX(side);
		this.setHelpingPtY(side);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(this.getStroke()));
		g2.setColor(this.getColor());
		if (this.isIsFilled()) {
			g2.fillRect(this.getCornerPt().x, this.getCornerPt().y, side, side);
		} else {
			g2.drawRect(this.getCornerPt().x, this.getCornerPt().y, side, side);
		}
	}

	public void edit(Graphics g, String s) {

		this.setIsEdited(true);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(this.getStroke()));
		g2.setColor(this.getColor());
		if (this.isIsFilled()) {
			g2.fillRect(this.getCornerPt().x, this.getCornerPt().y, this.getHelpingPtY(), this.getHelpingPtY());
		} else {
			g2.drawRect(this.getCornerPt().x, this.getCornerPt().y, this.getHelpingPtY(), this.getHelpingPtY());
		}
	}

	public void move(Point cPt, Point pPt) {
		this.getCornerPt().translate((int) (cPt.getX() - pPt.getX()), (int) (cPt.getY() - pPt.getY()));
	}

	public boolean contains(float x, float y) {

		int x1 = (int) this.getCornerPt().x;
		int y1 = (int) this.getCornerPt().y;
		int width = this.getHelpingPtX();
		int height = this.getHelpingPtX();
		if ((x >= x1 && x <= (x1 + width)) && (y >= y1 && y <= (y1 + height))) {
			return true;
		}
		return false;
	}
}
