package com.hulkstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hulkstore.util.RandomString;
import com.hulkstore.vo.ProductVO;
import com.hulkstore.vo.ResponseVO;

@Path("/products")
public class ProductService {

	private static List<ProductVO> products = new ArrayList<ProductVO>();

	@POST
	@Path("/getProducts")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseVO getProducts() {
		ResponseVO result = new ResponseVO();
		if (ProductService.products.isEmpty()) {
			result.setError(true);
			result.setMessage("No hay datos");
		} else {
			result.setProducts(ProductService.products);
		}
		return result;
	}

	@POST
	@Path("/getProduct")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseVO getProduct(ProductVO prod) {
		ResponseVO result = new ResponseVO();
		boolean find = false;
		for (ProductVO product : ProductService.products) {
			if (product.getId().equals(prod.getId())) {
				result.setProduct(product);
				find = true;
			}
		}
		if (!find) {
			result.setMessage("Producto no encontrado");
			result.setError(true);
		}
		return result;
	}

	@POST
	@Path("/setProduct")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseVO setProduct(ProductVO prod) {
		ResponseVO result = new ResponseVO();
		RandomString id = new RandomString(8, ThreadLocalRandom.current());
		prod.setId(id.nextString());
		if (prod.getQuantity() == null || prod.getQuantity() < 0) {
			prod.setQuantity(0);
		}
		ProductService.products.add(prod);
		result.setProduct(prod);
		result.setMessage("Agregado correctamente");
		return result;
	}

	@POST
	@Path("/putProduct")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseVO putProduct(ProductVO prod) {
		ResponseVO result = new ResponseVO();
		if (prod.getId().isEmpty()) {
			result.setMessage("Falta ID");
			result.setError(true);
		} else {
			for (ProductVO product : ProductService.products) {
				if (product.getId().equals(prod.getId())) {
					Integer index = ProductService.products.indexOf(product);
					ProductService.products.set(index, prod);
					result.setProduct(prod);
					result.setMessage("Producto Actualizado");
				}
			}
		}
		return result;
	}

	@POST
	@Path("/deleteProduct")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseVO deleteProduct(ProductVO prod) {
		ResponseVO result = new ResponseVO();
		if (prod.getId().isEmpty()) {
			result.setMessage("Falta ID");
			result.setError(true);
		} else {
			for (ProductVO product : ProductService.products) {
				if (product.getId().equals(prod.getId())) {
					result.setProduct(product);
					ProductService.products.remove(product);
					result.setMessage("Producto Eliminado");
					return result;
				}
			}
		}
		result.setError(true);
		result.setMessage("No se encuentra el ID");
		return result;
	}

}
