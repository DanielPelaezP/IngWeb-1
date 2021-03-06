/**
 * 
 */
package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import co.edu.udea.iw.DAO.ClienteDAO;
import co.edu.udea.iw.DAO.DataSource;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.exception.MyException;

/**
 * @author dcamilo.bedoya
 *
 */
public class ClienteDAOImp implements ClienteDAO {

	@Override
	public List<Cliente> obtener() throws MyException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Session session= null;
		Criteria criteria =null;
		
		try{
			session = DataSource.getInstance().getSession();
			criteria = session.createCriteria(Cliente.class);
			criteria.addOrder(Order.asc("fechaCreacion"));  //ordenar criteria
			//Retornar la lista de clientes (select * from clientes order by Fecha Creacion asc;)
			clientes = criteria.list();
		}catch(HibernateException e)
		{
			throw new MyException("Error consultando la lista de clientes", e);
		}
		return clientes;
	}

	@Override
	public void guardar(Cliente cliente) throws MyException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		try{
			session = DataSource.getInstance().getSession();
			tx = session.beginTransaction();
			
			session.save(cliente);
			tx.commit();   
		}catch(MyException e)
		{
			throw new MyException("Ocurrió un error guardando el cliente",e);
		}
		
	}

}
