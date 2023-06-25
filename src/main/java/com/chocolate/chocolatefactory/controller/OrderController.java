package com.chocolate.chocolatefactory.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.chocolate.chocolatefactory.model.*;
import com.chocolate.chocolatefactory.model.dto.*;
import com.chocolate.chocolatefactory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/orders")
public class OrderController {
    
    private final OrderService orderService;
    private final ProductService productService;
    private final WarehouseIngredientService warehouseIngredientService;

    private final MachineService machineService;

    private final IngredientService ingredientService;
    private final IngredientRequestService ingredientRequestService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, WarehouseIngredientService warehouseIngredientService, MachineService machineService,
                           IngredientRequestService ingredientRequestService, IngredientService ingredientService){

        this.orderService = orderService;
        this.productService = productService;
        this.warehouseIngredientService = warehouseIngredientService;
        this.machineService = machineService;
        this.ingredientRequestService = ingredientRequestService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/getCompletedOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<OrderDTO>> getCompletedOrders(){
        List<Order> orders = orderService.findByStatus(OrderStatus.Completed);

        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        for(Order order : orders){

            UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

            List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
            for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                orderedProductsDTO.add(orderedProductDTO);
            }

            OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
            ordersDTO.add(orderDTO);
            
        }
        
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getApprovedOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<OrderDTO>> getApprovedOrders(){
        List<Order> orders = orderService.findByStatus(OrderStatus.Approved);

        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        for(Order o : orders){
            List<OrderedProductDTO> orderedProductDTOS = new ArrayList<OrderedProductDTO>();
            for(OrderedProduct op : o.getOrderedProducts()){
                OrderedProductDTO opDTO = new OrderedProductDTO();
                opDTO.setId(op.getId());
                opDTO.setProductName(op.getProduct().getProductName());
                opDTO.setQuantity(op.getQuantity());
                orderedProductDTOS.add(opDTO);
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(o.getId());
            orderDTO.setAddress(o.getAddress());
            orderDTO.setCity(o.getCity());
            orderDTO.setDeliveryDate(String.valueOf(o.getDate()));
            orderDTO.setTotalPrice(o.getTotalPrice());
            orderDTO.setTotalWeight(o.getTotalWeight());
            orderDTO.setPriority(o.getPriority());
            orderDTO.setOrderedProductsDTO(orderedProductDTOS);

            ordersDTO.add(orderDTO);
        }
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getInProductionOrdersPM", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<OrderDTO>> getInProductionOrdersPM(){
        List<Order> orders = orderService.findByStatus(OrderStatus.In_Production);

        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        for(Order o : orders){
            List<OrderedProductDTO> orderedProductDTOS = new ArrayList<OrderedProductDTO>();
            for(OrderedProduct op : o.getOrderedProducts()){
                OrderedProductDTO opDTO = new OrderedProductDTO();
                opDTO.setId(op.getId());
                opDTO.setProductName(op.getProduct().getProductName());
                opDTO.setQuantity(op.getQuantity());
                orderedProductDTOS.add(opDTO);
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(o.getId());
            orderDTO.setAddress(o.getAddress());
            orderDTO.setCity(o.getCity());
            orderDTO.setDeliveryDate(String.valueOf(o.getDate()));
            orderDTO.setTotalPrice(o.getTotalPrice());
            orderDTO.setTotalWeight(o.getTotalWeight());
            orderDTO.setPriority(o.getPriority());
            orderDTO.setOrderedProductsDTO(orderedProductDTOS);

            ordersDTO.add(orderDTO);
        }
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderedProductsByOrder/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<OrderedProductDTO>> getOrderedProductsByOrder(@PathVariable Long id){
        Order order = this.orderService.findById(id);

        List<OrderedProduct> orderedProducts = order.getOrderedProducts();
        List<OrderedProductDTO> orderedProductDTOS = new ArrayList<OrderedProductDTO>();
        for(OrderedProduct op : orderedProducts){
            OrderedProductDTO opDTO = new OrderedProductDTO();
            opDTO.setProductName(op.getProduct().getProductName());
            opDTO.setQuantity(op.getQuantity());
            opDTO.setId(op.getId());

            orderedProductDTOS.add(opDTO);
        }

        return new ResponseEntity<>(orderedProductDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{product1}/{quantity1}/{product2}/{quantity2}/{product3}/{quantity3}/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<WarehouseIngredientDTO>> checkIngredientsInWarehouse(@PathVariable String product1, @PathVariable Integer quantity1, @PathVariable String product2,
                                                               @PathVariable Integer quantity2, @PathVariable String product3, @PathVariable Integer quantity3,
                                                               @PathVariable Long orderId) throws Exception{
        Boolean ret_val = true;
        List<Product> first_product = this.productService.findByName(product1);
        Integer first_quantity = quantity1;
        List<Product> second_product = this.productService.findByName(product2);
        Integer second_quantity = quantity2;
        List<Product> third_product = this.productService.findByName(product3);
        Integer third_quantity = quantity3;

        List<Recipe> first_recipe = new ArrayList<Recipe>();
        List<Recipe> second_recipe = new ArrayList<Recipe>();
        List<Recipe> third_recipe = new ArrayList<Recipe>();

        //za svaki produkt naci recepte
        for(Product p : first_product){
            first_recipe = p.getRecipes();
        }
        for(Product p : second_product){
            second_recipe = p.getRecipes();
        }
        for(Product p : third_product){
            third_recipe = p.getRecipes();
        }

        //stanje magacina
        List<WarehouseIngredient> warehouseIngredients = this.warehouseIngredientService.findAll();

        Hashtable<Long, Integer> hashtable = new Hashtable<>();

        for(Recipe r : first_recipe){
            if(hashtable.containsKey(r.getIngredient().getId())){
                hashtable.put(r.getIngredient().getId(), hashtable.get(r.getIngredient().getId()) + first_quantity*r.getQuantity());
            }else{
                hashtable.put(r.getIngredient().getId(), first_quantity*r.getQuantity());
            }
        }
        for(Recipe r : second_recipe){
            if(hashtable.containsKey(r.getIngredient().getId())){
                hashtable.put(r.getIngredient().getId(), hashtable.get(r.getIngredient().getId()) + second_quantity * r.getQuantity());
            }else{
                hashtable.put(r.getIngredient().getId(), second_quantity*r.getQuantity());
            }
        }
        for(Recipe r : third_recipe){
            if(hashtable.containsKey(r.getIngredient().getId())){
                hashtable.put(r.getIngredient().getId(), hashtable.get(r.getIngredient().getId()) + third_quantity * r.getQuantity());
            }else{
                hashtable.put(r.getIngredient().getId(), third_quantity*r.getQuantity());
            }
        }

        System.out.println("********************************************************");
        Iterator<Map.Entry<Long, Integer>> itr = hashtable.entrySet().iterator();

        Integer requestFlag = this.ingredientRequestService.findMaxRequestFlag() + 1;
        Order order = this.orderService.findById(orderId);
        Map.Entry<Long, Integer> entry = null;
        List<WarehouseIngredientDTO> warehouseIngredientDTOS = new ArrayList<WarehouseIngredientDTO>();
        while(itr.hasNext()){
            entry = itr.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            for(WarehouseIngredient wi : warehouseIngredients){
                if(entry.getKey() == wi.getIngredient().getId()){
                    WarehouseIngredientDTO whDTO = new WarehouseIngredientDTO();
                    whDTO.setIngredientName(wi.getIngredient().getName());
                    whDTO.setQuantity(wi.getQuantity() - entry.getValue());
                    whDTO.setId(wi.getId());
                    whDTO.setStatus(true);

                    if(entry.getValue() > wi.getQuantity()){
                        Integer quantity = entry.getValue() - wi.getQuantity();
                        Optional<Ingredient> ingredient = this.ingredientService.findOne(wi.getIngredient().getId());
                        IngredientRequest ingredientRequest = new IngredientRequest(quantity, requestFlag, new Date(), Date.from(order.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        ingredientRequest.setIngredient(ingredient.get());
                        this.ingredientRequestService.create(ingredientRequest);
                        whDTO.setStatus(false);
                    }
                    warehouseIngredientDTOS.add(whDTO);
                }
            }
        }
        System.out.println("********************************************************");

        return new ResponseEntity<>(warehouseIngredientDTOS, HttpStatus.OK);
    }

    @PostMapping (value = "/{product1}/{quantity1}/{product2}/{quantity2}/{product3}/{quantity3}/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<Boolean> startProduction(@PathVariable String product1, @PathVariable Integer quantity1, @PathVariable String product2,
                                                               @PathVariable Integer quantity2, @PathVariable String product3, @PathVariable Integer quantity3,
                                                   @PathVariable Long orderId){
        Boolean ret_val = true;
        List<Product> first_product = this.productService.findByName(product1);
        Integer first_quantity = quantity1;
        List<Product> second_product = this.productService.findByName(product2);
        Integer second_quantity = quantity2;
        List<Product> third_product = this.productService.findByName(product3);
        Integer third_quantity = quantity3;

        List<Recipe> first_recipe = new ArrayList<Recipe>();
        List<Recipe> second_recipe = new ArrayList<Recipe>();
        List<Recipe> third_recipe = new ArrayList<Recipe>();

        //za svaki produkt naci recepte
        for(Product p : first_product){
            first_recipe = p.getRecipes();
        }
        for(Product p : second_product){
            second_recipe = p.getRecipes();
        }
        for(Product p : third_product){
            third_recipe = p.getRecipes();
        }

        //stanje magacina
        List<WarehouseIngredient> warehouseIngredients = this.warehouseIngredientService.findAll();

        Hashtable<Long, Integer> hashtable = new Hashtable<>();

        for(Recipe r : first_recipe){
            if(hashtable.containsKey(r.getIngredient().getId())){
                hashtable.put(r.getIngredient().getId(), hashtable.get(r.getIngredient().getId()) + first_quantity*r.getQuantity());
            }else{
                hashtable.put(r.getIngredient().getId(), first_quantity*r.getQuantity());
            }
        }
        for(Recipe r : second_recipe){
            if(hashtable.containsKey(r.getIngredient().getId())){
                hashtable.put(r.getIngredient().getId(), hashtable.get(r.getIngredient().getId()) + second_quantity * r.getQuantity());
            }else{
                hashtable.put(r.getIngredient().getId(), second_quantity*r.getQuantity());
            }
        }
        for(Recipe r : third_recipe){
            if(hashtable.containsKey(r.getIngredient().getId())){
                hashtable.put(r.getIngredient().getId(), hashtable.get(r.getIngredient().getId()) + third_quantity * r.getQuantity());
            }else{
                hashtable.put(r.getIngredient().getId(), third_quantity*r.getQuantity());
            }
        }

        Iterator<Map.Entry<Long, Integer>> itr = hashtable.entrySet().iterator();

        Map.Entry<Long, Integer> entry = null;
        while(itr.hasNext()){
            entry = itr.next();
           // System.out.println(entry.getKey() + " -> " + entry.getValue());
            for(WarehouseIngredient wi : warehouseIngredients){
                if(entry.getKey() == wi.getIngredient().getId()){
                    if(entry.getValue() > wi.getQuantity()){
                        ret_val = false;
                    }
                }
            }
        }


        //nabaviti sve masine za produkte
        List<Machine> first_machines = this.machineService.findByType(MachineType.valueOf(product1));
        List<Machine> second_machines = this.machineService.findByType(MachineType.valueOf(product2));
        List<Machine> third_machines = this.machineService.findByType(MachineType.valueOf(product3));
        //iteriram kroz sve masine i punim input quantity
        Integer firstRecipeSum = 0;
        Integer counter = 0;
        ArrayList<Integer> numberOfUnits = new ArrayList<Integer>();
        for(Recipe r : first_recipe){
            firstRecipeSum += r.getQuantity();
        }
        for(Machine m : first_machines){
            Integer numberOfProducts = Math.floorDiv(m.getInputQuantity(),firstRecipeSum);
            System.out.println(m.getName() + " " + m.getType() + " " + m.getInputQuantity());
            System.out.println(numberOfProducts);
            if(first_quantity > 0){
                if(first_quantity - numberOfProducts > 0){
                    numberOfUnits.add((numberOfProducts * firstRecipeSum) / m.getWorkingDays() );
                    first_quantity -= numberOfProducts;
                }else{
                    numberOfUnits.add((first_quantity * firstRecipeSum)/ m.getWorkingDays());
                    first_quantity -= first_quantity;
                }
                counter++;
            }
        }
        if(first_quantity > 0){
            ret_val = false;
            System.out.println("^^^^^^^^^^^^^ NOT ENOUGH SPACE IN MACHINES^^^^^^^^^^^^^^^^^^^^^");
        }

        for(Integer i : numberOfUnits){
            System.out.println(i);
        }

        //second machines
        Integer secondRecipeSum = 0;
        Integer counter2 = 0;
        for(Recipe r : second_recipe){
            secondRecipeSum += r.getQuantity();
        }
        for(Machine m : second_machines){
            Integer numberOfProducts = Math.floorDiv(m.getInputQuantity(),secondRecipeSum);
            if(second_quantity > 0){
                if(second_quantity - numberOfProducts > 0){
                    numberOfUnits.add((numberOfProducts * secondRecipeSum) / m.getWorkingDays());
                    second_quantity -= numberOfProducts;
                }else{
                    numberOfUnits.add((second_quantity * secondRecipeSum)/m.getWorkingDays());
                    second_quantity -= second_quantity;
                }
                counter2++;
            }

        }
        if(second_quantity > 0){
            ret_val = false;
            System.out.println("^^^^^^^^^^^^^ NOT ENOUGH SPACE IN MACHINES^^^^^^^^^^^^^^^^^^^^^");
        }

        //third machines
        Integer thirdRecipeSum = 0;
        Integer counter3 = 0;
        for(Recipe r : third_recipe){
            thirdRecipeSum += r.getQuantity();
        }
        for(Machine m : third_machines){
            Integer numberOfProducts =Math.floorDiv( m.getInputQuantity(),thirdRecipeSum);
            if(third_quantity > 0){
                if(third_quantity - numberOfProducts > 0){
                    numberOfUnits.add((numberOfProducts * thirdRecipeSum) / m.getWorkingDays());
                    third_quantity -= numberOfProducts;
                }else{
                    numberOfUnits.add((third_quantity * thirdRecipeSum)/m.getWorkingDays());
                    third_quantity -= third_quantity;
                }
                counter3++;
            }
        }
        if(third_quantity > 0){
            ret_val = false;
            System.out.println("^^^^^^^^^^^^^ NOT ENOUGH SPACE IN MACHINES^^^^^^^^^^^^^^^^^^^^^");
        }

        if(ret_val == false){
            return new ResponseEntity<>(ret_val, HttpStatus.BAD_REQUEST);
        }

        Integer days = Collections.max(numberOfUnits)/24 + 1;
        Order order = this.orderService.findById(orderId);
        LocalDate orderDate = order.getDate();

        orderDate = LocalDate.now().plusDays(Long.valueOf(days));
        System.out.println("DATE : " + orderDate);

        orderService.changeOrderStatusAndDate(OrderStatus.In_Production, order.getId(), orderDate);
        Integer i = 0;
        for(Machine m : first_machines){
            if(i < counter){
                this.machineService.changeState(m.getId());
            }
            i++;
        }
         i = 0;
        for(Machine m : second_machines){
            if(i < counter2){
                this.machineService.changeState(m.getId());
            }
            i++;
        }
        i = 0;
        for(Machine m : third_machines){
            if(i < counter3){
                this.machineService.changeState(m.getId());
            }
            i++;
        }

        //stavim da su in_use i odredim datum
        //updateujem magacin //TODO
        this.warehouseIngredientService.update(hashtable);

        return new ResponseEntity<>(ret_val, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')"+"|| hasRole('ROLE_DRIVER')")
    public ResponseEntity<OrderDTO> findOneById(@PathVariable Long id){
        Order order = orderService.findById(id);

        if(order == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

        List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
        for(OrderedProduct orderedProduct : order.getOrderedProducts()){
            ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
            OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
            orderedProductsDTO.add(orderedProductDTO);
        }

        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getDeliveredOrders/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<OrderDTO>> getDeliveredOrdersByMonth(@PathVariable String month){
        List<Order> orders = orderService.getDeliveredOrdersByMonth(month);

        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        for(Order order : orders){

            UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

            List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
            for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                orderedProductsDTO.add(orderedProductDTO);
            }

            List<EngagementDTO> engagementsDTO = new ArrayList<>();

            for(Engagement engagement : order.getTour().getEngagements()){
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO, engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
                engagementsDTO.add(engagementDTO);
            }

            TourDTO tourDTO = new TourDTO(order.getTour().getCity(), order.getTour().getDate(), null, engagementsDTO);

            OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, tourDTO, orderedProductsDTO);
            ordersDTO.add(orderDTO);
            
        }
        
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getFinishedOrders/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<OrderDTO>> getFinishedOrdersByMonth(@PathVariable String month){
        List<Order> orders = orderService.getFinishedOrdersByMonth(month);

        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        for(Order order : orders){

           // UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

            List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
            for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                orderedProductsDTO.add(orderedProductDTO);
            }


            OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), null, null, orderedProductsDTO);
            ordersDTO.add(orderDTO);

        }

        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getInProductionOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<OrderDTO>> getInProductionOrders(){
        List<Order> orders = orderService.findByStatus(OrderStatus.In_Production);

        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        for(Order order : orders){

            UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

            List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
            for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                orderedProductsDTO.add(orderedProductDTO);
            }

            OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
            ordersDTO.add(orderDTO);
            
        }
        
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/changeOrderStatus")
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<List<OrderDTO>> changeOrderStatus(@RequestBody List<OrderDTO> ordersDTO){

        List<OrderDTO> changedOrdersDTO = new ArrayList<OrderDTO>();
        for(OrderDTO orderDTO : ordersDTO){
            orderService.changeOrderStatus(orderDTO.getStatus(), orderDTO.getId());
            Order order = orderService.findById(orderDTO.getId());

            UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

            List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
            for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                orderedProductsDTO.add(orderedProductDTO);
            }

            OrderDTO changedOrderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
            changedOrdersDTO.add(changedOrderDTO);
        }
        return new ResponseEntity<>(changedOrdersDTO, HttpStatus.OK);
    }
}
