package br.com.cristian.producer.controller;

import br.com.cristian.producer.service.MarketplaceService;
import br.com.cristian.producer.service.ProductService;
import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/marketplace" )
@RequiredArgsConstructor
public class MarketplaceController
{
    private final MarketplaceService marketplaceService;
    private final ProductService     productService;

    @GetMapping( "/product/{message}" )
    public ResponseEntity<String> produces( @PathVariable( "message" ) String message )
    {
        marketplaceService.produce( message );
        return ResponseEntity.ok().body( "Sending message" );
    }

    @PostMapping( "/product" )
    public ResponseEntity<ProductDTO> create( @RequestBody ProductDTO productDTO )
    {
        productService.createProduct( productDTO );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }
}