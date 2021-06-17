package Models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape {

	public Circle(int x1, int y1, Color color, float storke) {
		super(x1, y1, color, storke);
		this.setType("Circle");
	}

	public Circle(int x1, int y1, Color color, float storke, boolean drawFilled) {
		super(x1, y1, color, storke, drawFilled);
		this.setType("Circle");
	}

	@Override
	public void draw(Graphics g) {
		this.setCornerPt(new Point(Math.min(this.getX2(), this.getX1()), Math.min(this.getY2(), this.getY1())));

		int width = Math.max(Math.abs(this.getX2() - this.getX1()), Math.abs(this.getY2() - this.getY1()));
		this.setHelpingPtX(width);
		this.setHelpingPtY(width);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(this.getStroke()));
		g2.setColor(this.getColor());
		if (this.isIsFilled()) {
			g2.fillOval((int) this.getCornerPt().getX(), (int) this.getCornerPt().getY(), width, width);
		} else {
			g2.drawOval((int) this.getCornerPt().getX(), (int) this.getCornerPt().getY(), width, width);
		}
	}

	@Override
	public void edit(Graphics g, String s) {
		this.setIsEdited(true);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(this.getStroke()));
		g2.setColor(this.getColor());
		if (this.isIsFilled()) {
			g2.fillOval((int) this.getCornerPt().getX(), (int) this.getCornerPt().getY(), this.getHelpingPtX(),
					this.getHelpingPtX());
		} else {
			g2.drawOval((int) this.getCornerPt().getX(), (int) this.getCornerPt().getY(), this.getHelpingPtX(),
					this.getHelpingPtX());
		}

	}

	@Override
	public void move(Point current, Point prev) {
		this.getCornerPt().translate((int) (current.getX() - prev.getX()), (int) (current.getY() - prev.getY()));
	}

	public boolean contains(float x, float y) {
		int x1 = (int) this.getCornerPt().getX();
		int y1 = (int) this.getCornerPt().getY();

		int width = this.getHelpingPtX();
		int height = this.getHelpingPtX();

		if ((x >= x1 && x <= (x1 + width)) && (y >= y1 && y <= (y1 + height))) {
			return true;
		}
		return false;
	}
}
