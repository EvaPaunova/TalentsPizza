package model.dao;

import java.sql.SQLException;
import java.util.List;

import exception.InvalidArgumentsException;
import model.Address;
import model.User;

public interface IAddressDao {
	
	public List<Address> getAllUserAddresses(User user) throws SQLException, InvalidArgumentsException;
	
	public void deleteAddress(Address address) throws SQLException;
	
	public void updateAddress(Address address) throws SQLException;
	
	public void addNewAddress(Address address) throws SQLException;

}
