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
	
	
	public List<ProductsDto> GetDataProductsPeginate(int id,int start, int totalProductPage){
		List<ProductsDto> list = productsDao.GetDataProductsPeginate(id,start, totalProductPage);
		return list;	
		
	}

	public List<ProductsDto> GetAllProductsByID(int id) {
		List<ProductsDto> list = productsDao.GetAllProductsByID(id);
		return list;
	}
}
