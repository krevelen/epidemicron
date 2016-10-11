/* $Id$
 * 
 * Part of ZonMW project no. 50-53000-98-156
 * 
 * @license
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright (c) 2016 RIVM National Institute for Health and Environment 
 */
package nl.rivm.cib.episim.persist.dao;

import javax.measure.unit.NonSI;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.jscience.geography.coordinates.LatLong;

import nl.rivm.cib.episim.persist.AbstractDao;

/**
 * {@link LatLongDao}
 * 
 * @version $Id$
 * @author Rick van Krevelen
 */
@Embeddable
public class LatLongDao extends AbstractDao
{

	@Column( name = "LAT", nullable = false, updatable = false )//, precision = 3, scale = 18
	protected double latitude;

	@Column( name = "LON", nullable = false, updatable = false )//, precision = 3, scale = 18
	protected double longitude;

	/**
	 * @return
	 */
	public LatLong toLatLong()
	{
		return LatLong.valueOf( this.latitude, this.longitude,
				NonSI.DEGREE_ANGLE );
	}

	/**
	 * @param centroid
	 * @return
	 */
	public static LatLongDao of( final LatLong position )
	{
		final LatLongDao result = new LatLongDao();
		result.latitude = position.latitudeValue( NonSI.DEGREE_ANGLE );
		result.longitude = position.longitudeValue( NonSI.DEGREE_ANGLE );
		return result;
	}
}
