package DiamonShop.Service.User;

import org.springframework.stereotype.Service;

import DiamonShop.Dto.PaginatesDto;

@Service
public class PaginatesServiceImpl implements IPaginatesService {

	public PaginatesDto GetInfoPaginates(int totalData, int limit, int currentPage) {
		PaginatesDto paginates = new PaginatesDto();
		
	
		paginates.setLimit(limit);
		int totalPage = SetInfoTotalPage(totalData,limit);
		paginates.setTotalPage(totalPage);
		paginates.setCurrentPage(CheckCurrentPage(currentPage,totalPage ));
		
		paginates.setStart(FindStart(currentPage,limit));
		paginates.setEnd(FindEnd(paginates.getStart(),limit,totalData));
		
		return paginates;
	}

	private int FindEnd(int start, int limit, int totalData) {
		if (start + limit > totalData ) {
			return totalData;
		}
		return start +limit -1;
	}

	private int FindStart(int currentPage, int limit) {
		
		return (currentPage - 1)*limit +1;
	}

	private int CheckCurrentPage(int currentPage, int totalPage) {
		if (currentPage < 1) return 1;
		if (currentPage > totalPage) return totalPage;
		
		return currentPage;
	}

	private int SetInfoTotalPage(int totalData, int limit) {
		int totalPage = 0;
		totalPage =totalData/limit;		
		totalPage = totalPage * limit < totalData ? totalPage +1 : totalPage;
		return totalPage;
	}
}
