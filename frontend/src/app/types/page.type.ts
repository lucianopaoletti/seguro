/**
 * Interfaz de paginación usada en Spring.
 */
export interface Page<T> {
  content: T[];
  pageable: {
    pageNumber: number;
    pageSize: number;
    sort: {
      sorted: boolean;
      unsorted: boolean;
      empty: boolean;
    }
    offset: number;
    paged: boolean;
    unpaged: boolean;
  }
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;
  number: number;
  numberOfElements: number;
  size: number;
  empty: boolean;
}
