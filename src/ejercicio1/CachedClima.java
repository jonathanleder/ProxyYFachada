package ejercicio1;

import java.time.LocalDateTime;

public class CachedClima implements Clima {

	private Clima climaReal;
	private String climaCacheado;
	private LocalDateTime cachedTime;

	public CachedClima(Clima climaReal) {
		this.climaReal = climaReal;
		this.cachedTime = LocalDateTime.now();
	}

	@Override
	public String clima() {

		if (this.climaCacheado != null && LocalDateTime.now().isBefore(this.cachedTime.plusSeconds(30))) {
			System.out.println("esta en cache");
			return this.climaCacheado;
		}
		this.climaCacheado = this.climaReal.clima();
		System.out.println("Se cacheo");
		return this.climaCacheado;
	}

}
