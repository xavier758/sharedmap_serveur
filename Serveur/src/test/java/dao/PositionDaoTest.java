package dao;

import junit.framework.Assert;
import modele.Position;

import org.junit.Test;

public class PositionDaoTest {
	Integer idPosition;

	@Test
	public void positionTest() {
		// add test
		Position initialPosition = new Position(2.0, 3.0);

		int idPosition = PositionDao.addPosition(initialPosition);

		Position addedPosition = PositionDao.getPosition(initialPosition
				.getId());

		Assert.assertEquals(addedPosition.getId(), initialPosition.getId());
		Assert.assertEquals(addedPosition.getLongitude(),
				initialPosition.getLongitude());
		Assert.assertEquals(addedPosition.getLattitude(),
				initialPosition.getLattitude());

		// delete test
		PositionDao.deletePosition(idPosition);

		Position positionDeleted = PositionDao.getPosition(idPosition);

		Assert.assertNull(positionDeleted);
	}
}
