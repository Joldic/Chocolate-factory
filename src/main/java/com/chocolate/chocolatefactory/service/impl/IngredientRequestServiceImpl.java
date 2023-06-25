package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.IngredientRequest;
import com.chocolate.chocolatefactory.model.dto.IngredientDTO;
import com.chocolate.chocolatefactory.model.dto.IngredientRequestDTO;
import com.chocolate.chocolatefactory.repository.IngredientRepository;
import com.chocolate.chocolatefactory.repository.IngredientRequestRepository;
import com.chocolate.chocolatefactory.service.IngredientRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientRequestServiceImpl implements IngredientRequestService {

    @Autowired
    private IngredientRequestRepository ingredientRequestRepository;

    @Override
    public List<IngredientRequestDTO> findAll() throws Exception{

        List<IngredientRequest> ingredientRequests = ingredientRequestRepository.findAll();
        List<IngredientRequestDTO> ingredientRequestDTOS = new ArrayList<>();

        for(IngredientRequest ingredientRequest : ingredientRequests){
            IngredientRequestDTO ingredientRequestDTO = new IngredientRequestDTO(
                    ingredientRequest.getId(),
                    ingredientRequest.getQuantity(),
                    ingredientRequest.getRequestFlag(),
                    ingredientRequest.getCreationDate().toString(),
                    ingredientRequest.getDeliveryDeadlineDate().toString()
            );
            ingredientRequestDTOS.add(ingredientRequestDTO);
        }

        return ingredientRequestDTOS;
    }

    @Override
    public Integer findMaxRequestFlag(){
        Integer max = 0;
        List<IngredientRequest> ingredientRequests = this.ingredientRequestRepository.findAll();
        for(IngredientRequest i : ingredientRequests){
            if(i.getRequestFlag() > max){
                max = i.getRequestFlag();
            }
        }
        return max;
    }

    @Override
    public IngredientRequest create(IngredientRequest ingredientRequest){
        return this.ingredientRequestRepository.save(ingredientRequest);
    }

    @Override
    public List<IngredientRequestDTO> getLatest(){
        Integer max_flag = -1;
        List<IngredientRequest> ingredientRequests = this.ingredientRequestRepository.findAll();
        List<IngredientRequestDTO> ingredientRequestDTOS = new ArrayList<IngredientRequestDTO>();

        for(IngredientRequest ir : ingredientRequests){
            if(ir.getRequestFlag() > max_flag){
                max_flag = ir.getRequestFlag();
            }
        }

        for(IngredientRequest ir : ingredientRequests){
            if(ir.getRequestFlag() == max_flag){
                IngredientRequestDTO ingredientRequestDTO = new IngredientRequestDTO();
                ingredientRequestDTO.setId(ir.getId());
                ingredientRequestDTO.setQuantity(ir.getQuantity());
                ingredientRequestDTO.setCreationDate(String.valueOf(ir.getCreationDate()));
                ingredientRequestDTO.setDeliveryDeadlineDate(String.valueOf(ir.getDeliveryDeadlineDate()));
                ingredientRequestDTO.setRequestFlag(ir.getRequestFlag());
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(ir.getIngredient().getName());
                ingredientRequestDTO.setIngredientDTO(ingredientDTO);

                ingredientRequestDTOS.add(ingredientRequestDTO);
            }
        }

        return ingredientRequestDTOS;
    }
}
