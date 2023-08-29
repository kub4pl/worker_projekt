package com.example.workerapplication.converter;

import com.example.workerapplication.dto.WorkerDto;
import com.example.workerapplication.model.Worker;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkerToWorkerDtoConverter implements Converter<Worker, WorkerDto> {




        @Override
        public WorkerDto convert(Worker source) {
            WorkerDto workerDto = new WorkerDto();
            workerDto.setName(source.getName());
            workerDto.setSurname(source.getSurname());
            workerDto.setPESEL(source.getPESEL());
            workerDto.setAccountNumber(source.getAccountNumber());
            workerDto.setWorkerType(source.getWorkerType());
            workerDto.setNIP(source.getNIP());
            return workerDto;
        }
    }

