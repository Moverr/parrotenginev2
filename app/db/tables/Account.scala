package tables

import org.joda.time.DateTime
//import defaults ::



case class Account(
                    id: Long = 0L,
                    owner: Long,
                    name: String,
                    created_on: DateTime,
                    updated_on: Option[DateTime],
                    author_id: Long,
                    updated_by: Long,
                    external_id: String
                  )

