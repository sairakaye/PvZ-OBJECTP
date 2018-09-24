package mp.pvzv2.game.model;

import java.awt.Point;

public class Map {
	public static String getPlantButton(int x, int y) {
		if ((x >= 0 && x <= 209) && (y >= 262 && y <= 373))
			return "Sunflower";
		else if ((x >= 0 && x <= 209) && (y >= 421 && y <= 530))
			return "Peashooter";
		else if ((x >= 0 && x <= 209) && (y >= 578 && y <= 688))
			return "CherryBomb";
		else
			return "None";
	}
	
	public static Cell getMapCell(int x, int y) {
		int row = -1;
		int column = -1;
		
		if (y >= 58 && y < 216)
			row = 0;
		else if (y >= 216 && y < 374)
			row = 1;
		else if (y >= 374 && y < 532)
			row = 2;
		else if (y >= 532 && y < 690)
			row = 3;
		else if (y >= 690 && y < 848)
			row = 4;
		
		if (x >= 300 && x < 432)
			column = 0;
		else if (x >= 432 && x < 564)
			column = 1;
		else if (x >= 564 && x < 696)
			column = 2;
		else if (x >= 696 && x < 828)
			column = 3;
		else if (x >= 828 && x < 960)
			column = 4;
		else if (x >= 960 && x < 1092)
			column = 5;
		else if (x >= 1092 && x < 1224)
			column = 6;
		else if (x >= 1224 && x < 1356)
			column = 7;
		else if (x >= 1356 && x < 1488)
			column = 8;
		
		return new Cell(row, column);
	}
	
	public static Point getMapCellPosition(Cell cell) {
		int y = 0;
		int x = 0;
		
		switch (cell.getRow()) {
			case 0: y = 58;
					break;
			case 1: y = 216;
					break;
			case 2: y = 374;
					break;
			case 3: y = 532;
					break;
			case 4: y = 690;
					break;
		}
		
		switch (cell.getColumn()) {
			case 0: x = 300;
					break;
			case 1: x = 432;
					break;
			case 2: x = 564;
					break;
			case 3: x = 696;
					break;
			case 4: x = 828;
					break;
			case 5: x = 960;
					break;
			case 6: x = 1092;
					break;
			case 7: x = 1224;
					break;
			case 8: x = 1356;
					break;
		}
		
		return new Point(x, y);
	}
	
	public static int getColumnPosition(int column) {
		int x = 0;
		
		switch (column) {
			case 0: x = 300;
					break;
			case 1: x = 432;
					break;
			case 2: x = 564;
					break;
			case 3: x = 696;
					break;
			case 4: x = 828;
					break;
			case 5: x = 960;
					break;
			case 6: x = 1092;
					break;
			case 7: x = 1224;
					break;
			case 8: x = 1356;
					break;
		}
		
		return x;
	}
}