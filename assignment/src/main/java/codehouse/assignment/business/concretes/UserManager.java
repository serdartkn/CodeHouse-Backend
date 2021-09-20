package codehouse.assignment.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codehouse.assignment.business.abstracts.UserService;
import codehouse.assignment.core.utilities.result.concretes.DataResult;
import codehouse.assignment.core.utilities.result.concretes.Result;
import codehouse.assignment.core.utilities.result.concretes.SuccessDataResult;
import codehouse.assignment.entities.concretes.User;
import codehouse.assignment.business.constants.Messages;
import codehouse.assignment.core.utilities.result.concretes.ErrorResult;
import codehouse.assignment.core.utilities.result.concretes.SuccessResult;
import codehouse.assignment.dataAccess.abstracts.UserDao;

@Service
public class UserManager implements UserService
{
	private UserDao userDao;
	@Autowired
	public UserManager(UserDao userDao) 
	{
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) 
	{
		if (userDao.existsByPhone(user.getPhone())!=true) 
		{
			this.userDao.save(user);
			return new SuccessResult(Messages.addedUser);
		}
		else
		{
			return new ErrorResult(Messages.errorRegisteredPhone);					
		}
	}

	@Override
	public Result delete(User user) 
	{
		this.userDao.delete(user);
		return new SuccessResult(Messages.deletedUser);
	}

	@Override
	public Result update(User user) 
	{
		Optional<User> controlUser = this.userDao.findByPhone(user.getPhone());
		
		if (controlUser.isEmpty()==false) 
		{
			if (controlUser.get().getId()==user.getId()) 
			{
				this.userDao.updateUserById(user.getFirstName(), user.getLastName(), user.getPhone(), user.getId());
				return new SuccessResult(Messages.updatedUser);
			}
		}
		else if (controlUser.isEmpty()==true) 
		{
			if (userDao.existsByPhone(user.getPhone())!=true) 
			{
				this.userDao.updateUserById(user.getFirstName(), user.getLastName(), user.getPhone(), user.getId());
				return new SuccessResult(Messages.updatedUser);
			}
		}
		return new ErrorResult(Messages.errorRegisteredPhone);
	}
	
	@Override
	public DataResult<List<User>> findAll() 
	{
		return new SuccessDataResult<List<User>>(this.userDao.findAll(), Messages.listedUser);
	}

	@Override
	public DataResult<Optional<User>> findById(int id) 
	{
		return new SuccessDataResult<Optional<User>>(this.userDao.findById(id), Messages.listedUser);
	}

	@Override
	public DataResult<Optional<User>> findByPhone(String phone) 
	{
		return new SuccessDataResult<Optional<User>>(this.userDao.findByPhone(phone), Messages.listedUser);
	}
}