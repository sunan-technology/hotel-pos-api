package com.sunan.supplierLedger;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Supplier;
import com.sunan.model.SupplierLedger;

@Repository
public interface SupplierLedgerRepository extends PagingAndSortingRepository<SupplierLedger, Integer> {

	@Query("SELECT SUM(s.credit-s.debit) FROM SupplierLedger s WHERE supplier= :supplier")
	public Double getSupplierBalanceBySupplierId(Supplier supplier);

	public List<SupplierLedger> findSupplierBySupplier(Supplier supplier);
}
