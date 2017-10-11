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
package nl.rivm.cib.episim.geard;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.inject.Singleton;
import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Frequency;

import io.coala.enterprise.Actor;
import io.coala.exception.ExceptionFactory;
import io.coala.math.QuantityUtil;
import io.coala.time.Accumulator;
import io.coala.time.Instant;
import io.reactivex.Observable;
import nl.rivm.cib.episim.model.disease.Afflicted;
import nl.rivm.cib.episim.model.disease.infection.Contagion.Contagium;
import nl.rivm.cib.episim.model.disease.infection.EpiCompartment;
import nl.rivm.cib.episim.model.disease.infection.Occupancy;
import nl.rivm.cib.episim.model.disease.infection.Pathogen;
import nl.rivm.cib.episim.model.disease.infection.Transmission;
import nl.rivm.cib.episim.model.disease.infection.TransmissionRoute;

/**
 * {@link TransmissionSpace}
 * 
 * @deprecated see {@link Contagium}
 * @version $Id$
 * @author Rick van Krevelen
 */
@Deprecated
public interface TransmissionSpace extends Actor<Transmission>
{

//	Collection<TransmissionRoute> getTransmissionRoutes();

	/**
	 * @return an {@link Observable} stream of {@link Occupancy} facts generated
	 *         by {@link Afflicted} occupants of this {@link TransmissionSpace}
	 */
	Observable<Occupancy> emitContacts();

	/**
	 * @return an {@link Observable} stream of {@link Transmission} facts
	 *         generated by {@link Afflicted} occupants of this
	 *         {@link TransmissionSpace}
	 */
	Observable<Transmission> emitTransmissions();

	/**
	 * @param infection the local {@link Pathogen}
	 * @return the respective {@link InfectionDynamics}
	 */
//	InfectionDynamics dynamicsOf( Pathogen infection );

	/**
	 * @param occupant the {@link Afflicted} arriving at this
	 *            {@link TransmissionSpace}
	 */
//	default void enter( final Afflicted occupant )
//	{
//		occupant.afflictions().forEach( ( infection, condition ) ->
//		{
//			if( infection instanceof Pathogen )
//				dynamicsOf( (Pathogen) infection )
//						.dynamicsOf( condition.compartment() )
//						.onEnter( occupant );
//		} );
//	}

	/**
	 * @param occupant the {@link Afflicted} leaving this
	 *            {@link TransmissionSpace}
	 */
//	default void leave( final Afflicted occupant )
//	{
//		occupant.afflictions().forEach( ( infection, condition ) ->
//		{
//			if( infection instanceof Pathogen )
//				dynamicsOf( (Pathogen) infection )
//						.dynamicsOf( condition.compartment() )
//						.onLeave( occupant );
//		} );
//	}

	/**
	 * {@link Afflicted}s staying at this {@link TransmissionSpace} may cause it
	 * to generate {@link Occupancy}s and {@link Transmission}s based on
	 * available {@link TransmissionRoute}s (e.g. contaminated objects, food,
	 * water, blood, ...)
	 * 
	 * @param visitor the temporary occupant {@link Afflicted}
	 * @param arrival the {@link Instant} of arrival
	 * @param departure the {@link Instant} of departure
	 */
//	default void stay( final Afflicted visitor, final Instant arrival,
//		final Instant departure )
//	{
//		at( arrival ).call( this::enter, visitor );
//		at( departure ).call( this::leave, visitor );
//	}

	@Singleton
	interface Factory
	{
		TransmissionSpace create( String id );

		// apply caching
	}

	/**
	 * @return a {@link Simple} instance of {@link TransmissionSpace}
	 */
//	static TransmissionSpace of( final ID id, final Scheduler scheduler,
//		final TransmissionRoute... routes )
//	{
//		return new Simple( id, scheduler, routes );
//	}

	/**
	 * {@link SimpleInfection} is a {@link Pathogen} and {@link Observer} of
	 * {@link Visit}s which in turn may trigger its transmission by generating
	 * {@link Exposure}s.
	 */
//	class Simple extends Identified.SimpleOrdinal<ID>
//		implements TransmissionSpace
//	{
//
//		private final Scheduler scheduler;
//
//		private final Collection<TransmissionRoute> routes;
//
//		private final Map<Pathogen, InfectionDynamics> infections = new ConcurrentHashMap<>();
//
//		private final transient Subject<Visit, Visit> contacts = PublishSubject
//				.create();
//
//		private final transient Subject<Exposure, Exposure> transmissions = PublishSubject
//				.create();
//
//		public Simple( final ID id, final Scheduler scheduler,
//			final TransmissionRoute... routes )
//		{
//			this.id = id;
//			this.scheduler = scheduler;
//			this.routes = routes == null ? Collections.emptyList()
//					: Arrays.asList( routes );
//		}
//
//		@Override
//		public Scheduler scheduler()
//		{
//			return this.scheduler;
//		}
//
//		@Override
//		public Collection<TransmissionRoute> getTransmissionRoutes()
//		{
//			return routes;
//		}
//
//		@Override
//		public InfectionDynamics dynamicsOf( final Pathogen infection )
//		{
//			synchronized( this.infections )
//			{
//				InfectionDynamics result = this.infections.get( infection );
//				if( result == null )
//				{
//					result = InfectionDynamics.of( this, infection );
//					this.infections.put( infection, result );
//				}
//				return result;
//			}
//		}
//
//		@Override
//		public Observable<Visit> emitContacts()
//		{
//			return this.contacts.asObservable();
//		}
//
//		@Override
//		public Observable<Exposure> emitTransmissions()
//		{
//			return this.transmissions.asObservable();
//		}
//	}

	/**
	 * {@link InfectionDynamics}
	 * 
	 * @version $Id$
	 * @author Rick van Krevelen
	 */
	interface InfectionDynamics
	{
		TransmissionSpace getSpace();

		Pathogen getInfection();

		//Indicator getPressure();

		CompartmentDynamics dynamicsOf( EpiCompartment compartment );

		/**
		 * @param location the {@link TransmissionSpace}
		 * @param infection the {@link Pathogen}
		 * @return a {@link Simple} instance of {@link InfectionDynamics}
		 */
		static InfectionDynamics of( final TransmissionSpace location,
			final Pathogen infection )
		{
			return new Simple( location, infection );
		}

		/**
		 * {@link Simple} default implementation of {@link InfectionDynamics}
		 * 
		 * @version $Id$
		 * @author Rick van Krevelen
		 */
		class Simple implements InfectionDynamics
		{
			private TransmissionSpace location;

			private Pathogen infection;

			private final Map<EpiCompartment, CompartmentDynamics> compartments = new ConcurrentSkipListMap<>();

			public Simple( final TransmissionSpace location,
				final Pathogen infection )
			{
				this.location = location;
				this.infection = infection;
			}

			@Override
			public TransmissionSpace getSpace()
			{
				return this.location;
			}

			@Override
			public Pathogen getInfection()
			{
				return this.infection;
			}

			@Override
			public CompartmentDynamics
				dynamicsOf( final EpiCompartment compartment )
			{
				synchronized( this.compartments )
				{
					CompartmentDynamics result = this.compartments
							.get( compartment );
					if( result == null )
					{
						result = CompartmentDynamics.of( getSpace(),
								getInfection(), compartment );
						this.compartments.put( compartment, result );
					}
					return result;
				}
			}
		}
	}

	/**
	 * {@link CompartmentDynamics}
	 * 
	 * @version $Id$
	 * @author Rick van Krevelen
	 */
	interface CompartmentDynamics
	{
		TransmissionSpace getSpace();

		Pathogen getInfection();

		EpiCompartment getCompartment();

		Map<Afflicted, Instant> getArrivals();

		VisitorDynamics dynamicsOf( Afflicted visitor );

		default void onEnter( final Afflicted visitor )
		{
			final Instant previous;
			if( (previous = getArrivals().put( visitor,
					getSpace().now() )) != null )
				throw ExceptionFactory.createUnchecked(
						"%s already entered {} on {}", visitor, getSpace(),
						previous );

			if( getCompartment().isInfective() )
			{

				//  TODO update force of infection as f(R0, D) and reschedule (advance)
				// pending transmissions, or cancel on prior departure

				//Map< Individual, >
//				final Amount<Duration> duration = null;
//				final Amount<Frequency> force = getInfection()
//						.getForceOfInfection( this, visitor,
//								getLocation().per( getInfection() )
//										.per( EpidemicCompartment.Simple.SUSCEPTIBLE )
//										.getArrivals().keySet(),
//								duration );
			}
		}

		default void onLeave( final Afflicted visitor )
		{
			final Instant arrival;
			if( (arrival = getArrivals().remove( visitor )) == null )
				throw ExceptionFactory.createUnchecked( "{} already left {}",
						visitor, getSpace() );
			arrival.toQuantity();
//			final Instant departure = getLocation().now();
//			for( Map.Entry<Individual, Instant> entry : getOccupantArrivals()
//					.entrySet() )
//			{
//				final Instant start = arrival.compareTo( entry.getValue() ) > 0
//						? arrival : entry.getValue();
//				final Duration overlap = Duration.between( start, departure );
//
//				// FIXME generate contact events from overlapping occupancy/vector infestation/contamination stays
//				// FIXME generate transmission events
//			}
		}

		static CompartmentDynamics of( final TransmissionSpace space,
			final Pathogen infection, final EpiCompartment compartment )
		{
			return new Simple( space, infection, compartment );
		}

		/**
		 * {@link Simple} implementation of {@link CompartmentDynamics}
		 * 
		 * @version $Id$
		 * @author Rick van Krevelen
		 */
		class Simple implements CompartmentDynamics
		{
			private final TransmissionSpace space;

			private final Pathogen infection;

			private final EpiCompartment compartment;

			private final Map<Afflicted, Instant> visitorArrivals = new ConcurrentSkipListMap<>();

			private final Map<Afflicted, VisitorDynamics> visitorDynamics = new ConcurrentSkipListMap<>();

			public Simple( final TransmissionSpace space,
				final Pathogen infection,
				final EpiCompartment compartment )
			{
				this.space = space;
				this.infection = infection;
				this.compartment = compartment;
			}

			@Override
			public TransmissionSpace getSpace()
			{
				return this.space;
			}

			@Override
			public Pathogen getInfection()
			{
				return this.infection;
			}

			@Override
			public EpiCompartment getCompartment()
			{
				return this.compartment;
			}

			@Override
			public Map<Afflicted, Instant> getArrivals()
			{
				return this.visitorArrivals;
			}

			@Override
			public VisitorDynamics dynamicsOf( final Afflicted visitor )
			{
				synchronized( this.visitorDynamics )
				{
					VisitorDynamics result = this.visitorDynamics
							.get( visitor );
					if( result == null )
					{
						final Quantity<Frequency> rate = null;
						result = VisitorDynamics.of( visitor,
								Accumulator.of( getSpace().scheduler(),
										QuantityUtil.ZERO, rate ) );
						this.visitorDynamics.put( visitor, result );
					}
					return result;
				}
			}
		}
	}

	/**
	 * {@link VisitorDynamics}
	 * 
	 * @version $Id$
	 * @author Rick van Krevelen
	 */
	interface VisitorDynamics
	{
		Afflicted getVisitor();

		Accumulator<Dimensionless> getAccumulator();

		/**
		 * @param visitor the {@link Afflicted} visiting
		 * @param accumulator the {@link Accumulator}
		 * @return a {@link Simple} instance of {@link VisitorDynamics}
		 */
		static VisitorDynamics of( final Afflicted visitor,
			final Accumulator<Dimensionless> accumulator )
		{
			return new Simple( visitor, accumulator );
		}

		/**
		 * {@link Simple} implementation of {@link VisitorDynamics}
		 * 
		 * @version $Id$
		 * @author Rick van Krevelen
		 */
		class Simple implements VisitorDynamics
		{
			private Afflicted visitor;

			private Accumulator<Dimensionless> accumulator;

			public Simple( final Afflicted visitor,
				final Accumulator<Dimensionless> accumulator )
			{
				this.visitor = visitor;
				this.accumulator = accumulator;
			}

			@Override
			public Afflicted getVisitor()
			{
				return this.visitor;
			}

			@Override
			public Accumulator<Dimensionless> getAccumulator()
			{
				return this.accumulator;
			}
		}
	}
}