package com.bitclave.node.repository.request

import com.bitclave.node.repository.models.RequestData
import com.bitclave.node.services.errors.DataNotSaved
import org.springframework.stereotype.Component

@Component
class PostgresRequestDataRepositoryImpl(val repository: RequestDataCrudRepository) :
        RequestDataRepository {

    override fun getByFrom(from: String, state: RequestData.RequestDataState): List<RequestData> {
        return repository.findByFromPkAndState(from, state)
    }

    override fun getByTo(to: String, state: RequestData.RequestDataState): List<RequestData> {
        return repository.findByToPkAndState(to, state)
    }

    override fun getByFromAndTo(from: String, to: String, state: RequestData.RequestDataState): List<RequestData> {
        return repository.findByFromPkAndToPkAndState(from, to, state)
    }

    override fun findById(id: Long): RequestData? {
        return repository.findOne(id)
    }

    override fun updateData(request: RequestData): RequestData {
        return repository.save(request) ?: throw DataNotSaved()
    }

}
