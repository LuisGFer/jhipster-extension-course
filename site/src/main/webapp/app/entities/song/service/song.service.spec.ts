import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISong } from '../song.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../song.test-samples';

import { SongService, RestSong } from './song.service';

const requireRestSample: RestSong = {
  ...sampleWithRequiredData,
  inclusionDate: sampleWithRequiredData.inclusionDate?.format(DATE_FORMAT),
};

describe('Song Service', () => {
  let service: SongService;
  let httpMock: HttpTestingController;
  let expectedResult: ISong | ISong[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SongService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Song', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const song = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(song).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Song', () => {
      const song = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(song).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Song', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Song', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Song', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addSongToCollectionIfMissing', () => {
      it('should add a Song to an empty array', () => {
        const song: ISong = sampleWithRequiredData;
        expectedResult = service.addSongToCollectionIfMissing([], song);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(song);
      });

      it('should not add a Song to an array that contains it', () => {
        const song: ISong = sampleWithRequiredData;
        const songCollection: ISong[] = [
          {
            ...song,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSongToCollectionIfMissing(songCollection, song);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Song to an array that doesn't contain it", () => {
        const song: ISong = sampleWithRequiredData;
        const songCollection: ISong[] = [sampleWithPartialData];
        expectedResult = service.addSongToCollectionIfMissing(songCollection, song);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(song);
      });

      it('should add only unique Song to an array', () => {
        const songArray: ISong[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const songCollection: ISong[] = [sampleWithRequiredData];
        expectedResult = service.addSongToCollectionIfMissing(songCollection, ...songArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const song: ISong = sampleWithRequiredData;
        const song2: ISong = sampleWithPartialData;
        expectedResult = service.addSongToCollectionIfMissing([], song, song2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(song);
        expect(expectedResult).toContain(song2);
      });

      it('should accept null and undefined values', () => {
        const song: ISong = sampleWithRequiredData;
        expectedResult = service.addSongToCollectionIfMissing([], null, song, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(song);
      });

      it('should return initial array if no Song is added', () => {
        const songCollection: ISong[] = [sampleWithRequiredData];
        expectedResult = service.addSongToCollectionIfMissing(songCollection, undefined, null);
        expect(expectedResult).toEqual(songCollection);
      });
    });

    describe('compareSong', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSong(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSong(entity1, entity2);
        const compareResult2 = service.compareSong(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSong(entity1, entity2);
        const compareResult2 = service.compareSong(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSong(entity1, entity2);
        const compareResult2 = service.compareSong(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
