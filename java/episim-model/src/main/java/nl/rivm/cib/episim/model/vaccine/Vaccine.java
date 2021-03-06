package nl.rivm.cib.episim.model.vaccine;

import java.util.Collection;
import java.util.Collections;

import javax.measure.quantity.Dimensionless;

import io.coala.enterprise.Actor;
import io.coala.random.ProbabilityDistribution;
import io.coala.random.QuantityDistribution;
import io.coala.time.Proactive;
import io.coala.time.Scheduler;
import nl.rivm.cib.episim.model.disease.Condition;
import nl.rivm.cib.episim.model.disease.infection.Pathogen;

/**
 * {@link Vaccine}s trigger the immune system to protect against some
 * {@link Pathogen}
 * 
 * @version $Id$
 * @author Rick van Krevelen
 */
public interface Vaccine extends Proactive
{

	Collection<Pathogen> getTargets();

	/**
	 * @param condition the {@link Condition} to improve
	 * @return a {@link ProbabilityDistribution} of the <em>actual</em> efficacy
	 *         for specified {@link Individual} 's traits (age, sex, &hellip;)
	 */
	ProbabilityDistribution<Boolean> getEfficacy( Condition condition );

	/**
	 * @param person the {@link Individual} to vaccinate
	 * @return a {@link ProbabilityDistribution} of the <em>actual</em> delivery
	 *         method comfort (e.g. intravenous, needle-free patch, inhaled,
	 *         oral, micro-needle arrays, stratum corneum disruption) for
	 *         specified {@link Individual}'s traits (age, sex, &hellip;)
	 */
	QuantityDistribution<Dimensionless> getComfort( Actor.ID person );

	/**
	 * {@link Simple} implementation of {@link Vaccine}
	 * 
	 * @version $Id$
	 * @author Rick van Krevelen
	 */
	class Simple implements Vaccine
	{

		/**
		 * @param scheduler the {@link Scheduler}
		 * @param target the target {@link Pathogen}
		 * @param efficacy <em>actual</em> efficacy distribution for everyone
		 * @param comfort <em>actual</em> delivery method comfort distribution
		 *            for everyone
		 * @return a {@link Simple} instance of {@link Vaccine}
		 */
		public static Simple of( final Scheduler scheduler,
			final Pathogen target,
			final ProbabilityDistribution<Boolean> efficacy,
			final QuantityDistribution<Dimensionless> comfort )
		{
			return new Simple( scheduler, Collections.singleton( target ),
					efficacy, comfort );
		}

		private final Scheduler scheduler;

		private final Collection<Pathogen> targets;

		private final ProbabilityDistribution<Boolean> efficacy;

		private final QuantityDistribution<Dimensionless> comfort;

		/**
		 * {@link Simple} constructor
		 * 
		 * @param scheduler the {@link Scheduler}
		 * @param targets the target {@link Pathogen}s
		 */
		public Simple( final Scheduler scheduler,
			final Collection<Pathogen> targets,
			final ProbabilityDistribution<Boolean> efficacy,
			final QuantityDistribution<Dimensionless> comfort )
		{
			this.scheduler = scheduler;
			this.targets = targets;
			this.efficacy = efficacy;
			this.comfort = comfort;
		}

		@Override
		public Scheduler scheduler()
		{
			return this.scheduler;
		}

		@Override
		public Collection<Pathogen> getTargets()
		{
			return this.targets;
		}

		@Override
		public ProbabilityDistribution<Boolean>
			getEfficacy( final Condition condition )
		{
			return this.efficacy;
		}

		@Override
		public QuantityDistribution<Dimensionless>
			getComfort( final Actor.ID person )
		{
			return this.comfort;
		}

	}
}