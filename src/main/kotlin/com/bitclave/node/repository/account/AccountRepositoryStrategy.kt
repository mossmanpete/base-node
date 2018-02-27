package com.bitclave.node.repository.account

import com.bitclave.node.repository.RepositoryStrategy
import com.bitclave.node.repository.RepositoryStrategyType
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("AccountRepository")
class AccountRepositoryStrategy(
        @Qualifier("postgres")
        private val postgres: PostgresAccountRepositoryImpl

) : RepositoryStrategy<AccountRepository> {

    override fun changeStrategy(type: RepositoryStrategyType): AccountRepository {
        return when (type) {
            RepositoryStrategyType.POSTGRES -> postgres
            RepositoryStrategyType.ETHEREUM -> postgres
        }
    }

}
