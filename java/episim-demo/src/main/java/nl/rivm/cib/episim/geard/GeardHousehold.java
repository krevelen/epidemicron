package nl.rivm.cib.episim.geard;

import java.util.Collections;
import java.util.Objects;

import io.coala.rx.RxCollection;
import io.coala.time.Scheduler;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * {@link GeardHousehold} adopts elements from
 * <a href= "https://github.com/nlgn/sim-demog">Python code by Nic Geard</a>
 * 
 * @version $Id$
 * @author Rick van Krevelen
 */
@Deprecated
public interface GeardHousehold<T extends HouseholdParticipant> extends Population<T>
{

	HouseholdPopulation<T> population();

	@SuppressWarnings( "unchecked" )
	default void onMoveHouse( final T homeLeaver )
	{
		Objects.requireNonNull( homeLeaver );
		emit( DemographicEvent.Builder.of( MoveHouse.class, now() )
				.withDepartures( Collections.singleton( homeLeaver ) )
				.build() );
	}

	@SuppressWarnings( "unchecked" )
	default void onAbandoned()
	{
		emit( DemographicEvent.Builder.of( Abandon.class, now() ).build() );
	}

	/**
	 * {@link MoveHouse}
	 */
	public class MoveHouse<T extends Participant> extends DemographicEvent<T>
	{
	}

	/**
	 * {@link MoveHouse}
	 */
	public class Abandon<T extends Participant> extends DemographicEvent<T>
	{
	}

	static <T extends HouseholdParticipant> GeardHousehold<T> of( final String name,
		final HouseholdPopulation<T> population, final RxCollection<T> members )
	{
		final GeardHousehold<T> result = new GeardHousehold<T>()
		{
			private final ID id = ID.of( name );

			private final Subject<DemographicEvent<T>> events = PublishSubject
					.create();

			@Override
			public Scheduler scheduler()
			{
				return population.scheduler();
			}

			@Override
			public HouseholdPopulation<T> population()
			{
				return population;
			}

			@Override
			public RxCollection<T> members()
			{
				return members;
			}

			@Override
			public ID id()
			{
				return this.id;
			}

			@Override
			public Observable<DemographicEvent<T>> events()
			{
				return this.events;
			}

			@Override
			public void emit( final DemographicEvent<T> event )
			{
				this.events.onNext( event );
			}
		};
		population.households().add( result );
		result.events().subscribe( event ->
		{
			population.emit( event );
		}, error ->
		{
		} );
		return result;
	}
}
