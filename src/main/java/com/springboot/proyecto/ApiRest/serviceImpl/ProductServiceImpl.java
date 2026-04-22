package com.springboot.proyecto.ApiRest.serviceImpl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.proyecto.ApiRest.dto.ProductDto;
import com.springboot.proyecto.ApiRest.entities.ProductEntities;
import com.springboot.proyecto.ApiRest.exceptions.ServiceException;
import com.springboot.proyecto.ApiRest.repository.ProductRepository;
import com.springboot.proyecto.ApiRest.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntities findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Producto no encontrado con id: " + id));
    }

    @Override
    public ProductEntities save(ProductEntities product) {
        return productRepository.save(product);
    }

    @Override
    public Page<ProductEntities> getProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductEntities saveFromDto(ProductDto dto) {
        ProductEntities product = ProductEntities.builder()
                .fv_nombre(dto.getFv_nombre())
                .fv_descripcion(dto.getFv_descripcion())
                .fv_marca(dto.getFv_marca())
                .fn_precio(dto.getFn_precio())
                .fi_stock(dto.getFi_stock())
                .build();
        return productRepository.save(product);
    }

    @Override
    public ProductEntities updateFromDto(Long id, ProductDto dto) {
        //verificamos que el producto exista
        ProductEntities productoExistente = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("No se puede actualizar, producto no encontrado con id: " + id));

        //actualizamos los campos con la información del DTO
        productoExistente.setFv_nombre(dto.getFv_nombre());
        productoExistente.setFv_descripcion(dto.getFv_descripcion());
        productoExistente.setFv_marca(dto.getFv_marca());
        productoExistente.setFn_precio(dto.getFn_precio());
        productoExistente.setFi_stock(dto.getFi_stock());

        //guardamos los cambios en el registro existente
        return productRepository.save(productoExistente);
    }

    @Override
    @Transactional
    public void importProductsFromExcel(MultipartFile file) {
        //abrimos el flujo de datos del archivo y creamos el libro de Excel (Workbook).
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0); 
            Iterator<Row> rows = sheet.iterator(); //recorrer fila por fila.

            //salta la fila 1 
            if (rows.hasNext()) {
                rows.next(); 
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                
                //si la fila está totalmente vacía o la primera celda no tiene texto, la ignoramos.
                if (currentRow == null || currentRow.getCell(0) == null || currentRow.getCell(0).getCellType() == CellType.BLANK) {
                    continue; 
                }

                //Precio y Stock sean numéricas.
                if (currentRow.getCell(3).getCellType() != CellType.NUMERIC || 
                    currentRow.getCell(4).getCellType() != CellType.NUMERIC) {
                    throw new ServiceException("Error fila " + (currentRow.getRowNum() + 1) + ": Precio y Stock deben ser números.");
                }

                //extrae los valores del Excel a variables de Java.
                String nombre = currentRow.getCell(0).getStringCellValue();
                BigDecimal precio = new BigDecimal(currentRow.getCell(3).getNumericCellValue());
                int stock = (int) currentRow.getCell(4).getNumericCellValue();
                
                //no permitimos valores negativos o en cero para precio y stock.
                if (precio.compareTo(BigDecimal.ZERO) <= 0 || stock <= 0) {
                    throw new ServiceException("Error fila " + (currentRow.getRowNum() + 1) + ": Valores deben ser mayores a 0.");
                }

                //consultamos al Repository si el nombre ya existe en Postgres.
                if (productRepository.existsByFvNombre(nombre)) {
                    throw new ServiceException("El producto '" + nombre + "' ya existe.");
                }

                //creamos un nuevo objeto de la Entidad y le pasamos los datos del Excel.
                ProductEntities producto = new ProductEntities();
                producto.setFv_nombre(nombre);
                producto.setFv_descripcion(currentRow.getCell(1).getStringCellValue());
                producto.setFv_marca(currentRow.getCell(2).getStringCellValue());
                producto.setFn_precio(precio);
                producto.setFi_stock(stock);
                
                //guardamos en la base de datos. El ID se genera solo por ser IDENTITY.
                productRepository.save(producto);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al leer Excel: " + e.getMessage());
        }
    }
}
