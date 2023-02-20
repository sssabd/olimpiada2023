package com.example.domain.usecases

import com.example.domain.repository.ServicesRepository

class UpdateServicesUseCase(private val servicesRepository: ServicesRepository) {
    operator fun invoke() {
        servicesRepository.updateServices()
    }
}
