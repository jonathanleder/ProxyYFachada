package modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class ProxyInformacionTelefonos implements Set<Telefono> {

	private Integer idPersona;
	private Set<Telefono> telefonos;
	private TelefonoDao daoParaInfo;

	public ProxyInformacionTelefonos(Integer idPersona, TelefonoDao lugarInfo) {
		Objects.requireNonNull(idPersona);
		Objects.requireNonNull(lugarInfo);

		this.idPersona = idPersona;
		this.telefonos = new HashSet<>();
		this.daoParaInfo = lugarInfo;
	}

	@Override
	public int size() {
		return this.telefonos.size();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		try {
			this.telefonos = daoParaInfo.telefonosDePersonaPorId(this.idPersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return telefonos.toArray(a);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Telefono> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Telefono e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Telefono> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
