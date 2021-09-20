package codehouse.assignment.business.abstracts;

import java.util.Optional;

import codehouse.assignment.core.business.CrudOperationRepo;
import codehouse.assignment.entities.concretes.User;
import codehouse.assignment.core.utilities.result.concretes.DataResult;

public interface UserService extends CrudOperationRepo<User>
{
	DataResult<Optional<User>> findByPhone(String phone);
}