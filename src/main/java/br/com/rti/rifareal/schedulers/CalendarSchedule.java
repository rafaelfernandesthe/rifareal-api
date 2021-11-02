package br.com.rti.rifareal.schedulers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.repositories.RifaRepository;

@Component
public class CalendarSchedule {

	private static Logger logger = LoggerFactory.getLogger( CalendarSchedule.class );

	@Autowired
	private RifaRepository rifaRepository;

	@Scheduled( cron = "0 55 23 * * *" )
	public void adjustEndDate() {
		logger.info( "adjustEndDate() - ajustando datas dos sorteios!" );

		List<Rifa> all = rifaRepository.findAll();

		all.forEach( rifa -> {
			int percent = 100 - ( ( rifa.getRifasRestantes() / rifa.getRifasTotal() ) * 100 );
			logger.info( "rifa={} \n percentual restante={}", rifa.toString(), percent );

			if ( percent > 5 ) {
				Calendar c = Calendar.getInstance();
				boolean change = false;
				if ( rifa.getDataSorteio().after( new Date() ) ) {
					logger.info( "--> data do sorteio passou, postergando 2 sabados a frente" );
					c = Calendar.getInstance();
					c.set( Calendar.DAY_OF_WEEK, Calendar.SUNDAY );
					c.add( Calendar.DAY_OF_MONTH, 14 ); // 2 sabados a frente
					rifa.setDataSorteio( c.getTime() );
					change = true;
				} else {
					c = Calendar.getInstance();
					c.add( Calendar.DAY_OF_MONTH, 7 );
					// se o prazo estao nos proximos 7 dias, posterga mais 7
					if ( rifa.getDataSorteio().after( c.getTime() ) ) {
						logger.info( "--> data do sorteio em 7 dias, postergando 7 dias a frente" );
						c = Calendar.getInstance();
						c.setTime( rifa.getDataSorteio() );
						c.add( Calendar.DAY_OF_MONTH, 7 ); // proxima semana
						rifa.setDataSorteio( c.getTime() );
						change = true;
					}
				}
				if ( change ) {
					// enviar email com mudança
					rifaRepository.save( rifa );
				}
			}
		} );

	}

}
