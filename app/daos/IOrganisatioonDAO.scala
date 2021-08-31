package daos
import db.tables.{Organization, OrganizationTable}
import slick.lifted.TableQuery

import scala.concurrent.Future

trait IOrganisatioonDAO {

  val orgTable: TableQuery[OrganizationTable]

  /*
 *
 * Get Organisation by owner

  */
  def list(owner: Long, offset: Int, limit: Int): Future[Seq[Organization]]

  /*
  *
  * Create Organisation
 */
  def create(name: String, details: String, owner: Long): Future[Organization]
}
