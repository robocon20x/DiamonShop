package DiamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.ProductsDao;
import DiamonShop.Dto.ProductsDto;


@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	ProductsDao productDao = new ProductsDao();

	public List<ProductsDto> GetProductByID(long id) {

		return productDao.GetProductByID(id);
	}

	public List<ProductsDto> GetProductByIDCategory(int id) {
		
		return productDao.GetAllProductsByID(id);
	}

}
