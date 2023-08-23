function pagination(page, totalCount) {
  var pageLimit = 5;
  const lastPage = Math.ceil(totalCount / pageLimit);
  var startPage = parseInt((page - 1) / pageLimit) * pageLimit + 1;

  document.getElementById('pagination').innerHTML = `
	${startPage <= 10
	  ? `<div class="prev">` 
	  : `<div class="prev" id="prevActive">`}
	  <div class="prevArrow"></div>
	</div>
	${new Array(10).fill(1).map((_, index) =>
      index + startPage <= lastPage
        ? `<div class="pageNumber${index + startPage == page ? ' active' : ''}" id="${index + startPage}">${`${index + startPage}`}</div>`
        : ''
    ).join('')}
	${startPage + 10 <= lastPage
      ? `<div class="next" id="nextActive">`
      : `<div class="next">`}
	  <div class="nextArrow"></div>
	</div>
	`;

  const pageNums = document.querySelectorAll('.pageNumber');

  pageNums.forEach(cell => {
    cell.addEventListener('click', e => {
      let children = e.target.parentElement.children;

      for (var i = 0; i < children.length; i++) {
        e.target.parentElement.children[i].classList.remove('active');
      }

      e.currentTarget.classList.add('active');
      page = Number(e.target.id);

      commentList(page)
    });
  });

  const prev = document.getElementById('prevActive');
  const next = document.getElementById('nextActive');

  if (prev) {
    prev.addEventListener('click', () => {
      page = Math.floor(page / 10 - 1) * 10 + 1;

      commentList(page)
    });
  }
  if (next) {
    next.addEventListener('click', () => {
      page = Math.ceil(page / 10) * 10 + 1;

      commentList(page)
    });
  }
}
