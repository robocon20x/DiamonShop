package DiamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.ProductsDao;
import DiamonShop.Dto.ProductsDto;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ProductsDao productsDao;
	
	public List<ProductsDto> GetDataProductsPeginate(int start, int end){
		List<ProductsDto> list = productsDao.GetDataProductsPeginate(start, end);
		return list;
		
	}

	public List<ProductsDto> GetAllProductsByID(int id) {
		List<ProductsDto> list = productsDao.GetAllProductsByID(id);
		return list;
	}
}
