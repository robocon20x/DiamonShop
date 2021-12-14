package DiamonShop.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Dto.ProductsDto;
import DiamonShop.Dto.ProductsDtoMapper;

@Repository
public class ProductsDao extends BaseDao {
	
	private StringBuffer SqlString() {
		StringBuffer  sql = new StringBuffer();
		sql.append("SELECT p.id as id_product, ");
		sql.append("p.id_category, ");
		sql.append("p.sizes, ");
		sql.append("p.name, ");
		sql.append("p.price, ");
		sql.append("p.sale, ");
		sql.append("p.title, ");
		sql.append("p.highlight, ");
		sql.append("p.new_product, ");
		sql.append("p.details, ");
		sql.append("c.id as id_color, ");
		sql.append("c.name as name_color, ");
		sql.append("c.code as code_color, ");
		sql.append("c.img, ");
		sql.append("p.created_at, ");
		sql.append("p.updated_at ");
		sql.append("FROM products as p, colors as c ");
		sql.append("WHERE c.id_product = p.id ");
		
		return sql;
	}
	

	
	private String SqlProducts(boolean newProduct, boolean highlight) {
		StringBuffer  sql =  SqlString();
		if(highlight) {
			sql.append("and p.highlight = true ");
		}
		if(newProduct) {
			sql.append("and p.new_product = true ");
		}
		sql.append("GROUP by p.id,c.id_product ");
		sql.append("order by rand() ");
		if(highlight) {
			sql.append("limit 9 ");
		}
		if(newProduct) {
			sql.append("limit 12 ");
		}
		
		return sql.toString();
	}
	
	private String SqlProductsByID(int id) {
		StringBuffer  sql =  SqlString();
		sql.append("and id_category =  " + id+" ");
		
		return sql.toString();
	}
	
	private String SqlProductsPaginate(int id,int start, int totalProductPage) {
		StringBuffer  sql =  new StringBuffer();
		sql.append(SqlProductsByID(id));
		sql.append("limit  " + start+", "+totalProductPage +" ");
		
		return sql.toString();
	}
	
	public List<ProductsDto> GetDataProducts(){
		
		String sql = SqlProducts(false,true);
		List<ProductsDto> list = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return list;
	}
	
	public List<ProductsDto> GetAllProductsByID(int id){
		
		String sql = SqlProductsByID(id);
		List<ProductsDto> list = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return list;
	}
	
	public List<ProductsDto> GetDataProductsPeginate(int id,int start, int totalProductPage){
		
		String sql = SqlProductsPaginate(id,start,totalProductPage);
		List<ProductsDto> list = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return list;
	}



	public List<ProductsDto> GetProductByID(long id) {
		StringBuffer  sql =  SqlString();
		sql.append("and p.id ="+id +" ");
		sql.append("Limit 1 ");
		List<ProductsDto> list = _jdbcTemplate.query(sql.toString(), new ProductsDtoMapper());
		return list;
	}

	public ProductsDto FindProductByID(long id) {
		StringBuffer  sql =  SqlString();
		sql.append("and p.id ="+id +" ");
		sql.append("Limit 1 ");
		ProductsDto product = _jdbcTemplate.queryForObject(sql.toString(), new ProductsDtoMapper());
		return product;
	}

}
