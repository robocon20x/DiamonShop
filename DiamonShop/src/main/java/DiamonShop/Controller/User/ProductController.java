package DiamonShop.Controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Dto.ProductsDto;
import DiamonShop.Service.User.IProductService;

@Controller
public class ProductController extends BaseController{
	
	@Autowired
	private IProductService _productService;
	
	@RequestMapping(value = {"chi-tiet-san-pham/{id}"})
	public ModelAndView Index(@PathVariable long id) {
		
		_mvShare.addObject("product", _productService.GetProductByID(id));
		List<ProductsDto> idCategory = _productService.GetProductByID(id);
		_mvShare.addObject("productByIDCategory", _productService.GetProductByIDCategory(idCategory.get(0).getId_category()));
		_mvShare.setViewName("user/products/product");
		return _mvShare;
	}
}
